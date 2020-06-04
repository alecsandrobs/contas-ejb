package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.repository.ContatoRepository;
import com.stolk.alecsandro.obra.service.ContatoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.Response.Status.CREATED;

@Stateless
@Path("contatos")
@Consumes({APPLICATION_JSON, APPLICATION_XML})
@Produces({APPLICATION_JSON, APPLICATION_XML})
public class ContatoResource implements Serializable {

    @Inject
    private ContatoRepository repository;

    @Inject
    private ContatoService service;

    @GET
    public Response get() {
        List<Contato> contatos = repository.buscar();
        return Response.ok(contatos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Contato contato = new Contato();
        contato = repository.buscar(id);
        return Response.ok(contato).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = repository.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    public Response post(Contato contato) {
        service.cadastrar(contato);
        return Response.created(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @POST
    @Path("lista")
    public Response posts(List<Contato> contatos) {
        contatos.stream().forEach(contato -> service.cadastrar(contato));
        return Response.status(CREATED).build();
    }

    @PUT
    public Response put(Contato contato) {
        service.editar(contato);
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Contato contato) {
        service.editar(id, contato);
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @DELETE
    public Response delete(Contato contato) {
        service.excluir(contato);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}
