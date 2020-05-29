package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.repository.ContaRepository;
import com.stolk.alecsandro.obra.service.ContaService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Stateless
@Path("contas")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContaResource implements Serializable {

    private Conta conta = new Conta();

    @Inject
    private ContaRepository repository;

    @Inject
    private ContaService service;

    @GET
    public Response get() {
        List<Conta> contas = repository.buscar();
        return Response.ok(contas).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        this.conta = repository.buscar(id);
        return Response.ok(this.conta).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = repository.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    public Response post(Conta conta) {
        service.cadastrar(conta);
        return Response.created(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @POST
    @Path("lista")
    public Response posts(List<Conta> contas) {
        contas.stream().forEach(conta -> service.cadastrar(conta));
        return Response.status(CREATED).build();
    }

    @PUT
    public Response put(Conta conta) {
        service.editar(conta);
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Conta conta) {
        service.editar(id, conta);
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @DELETE
    public Response delete(Conta conta) {
        this.conta = conta;
        service.excluir(this.conta);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}
