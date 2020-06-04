package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.modelo.Fornecedor;
import com.stolk.alecsandro.obra.repository.FornecedorRepository;
import com.stolk.alecsandro.obra.service.FornecedorService;

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
@Path("fornecedores")
@Consumes({APPLICATION_JSON, APPLICATION_XML})
@Produces({APPLICATION_JSON, APPLICATION_XML})
public class FornecedorResource implements Serializable {

    @Inject
    private FornecedorRepository repository;

    @Inject
    private FornecedorService service;

    @GET
    public Response get() {
        List<Fornecedor> fornecedores = repository.buscar();
        return Response.ok(fornecedores).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = repository.buscar(id);
        return Response.ok(fornecedor).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = repository.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    public Response post(Fornecedor fornecedor) {
        service.cadastrar(fornecedor);
        return Response.created(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @POST
    @Path("lista")
    public Response posts(List<Fornecedor> fornecedores) {
        fornecedores.stream().forEach(fornecedor -> service.cadastrar(fornecedor));
        return Response.status(CREATED).build();
    }

    @PUT
    public Response put(Fornecedor fornecedor) {
        service.editar(fornecedor);
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Fornecedor fornecedor) {
        service.editar(id, fornecedor);
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @DELETE
    public Response delete(Fornecedor fornecedor) {
        service.excluir(fornecedor);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }

    @POST
    @Path("{fornecedorId}/contas")
    public Response postContas(@PathParam("fornecedorId") Long fornecedorId, Conta conta) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = repository.buscar(fornecedorId);
        fornecedor.adicionarConta(conta);
        return Response.created(URI.create(String.format("/fornecedores/%s/contas/%s", fornecedorId, conta.getId()))).build();
    }

    @DELETE
    @Path("{fornecedorId}/contas/{id}")
    public Response deleteContas(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = repository.buscar(fornecedorId);
        fornecedor.removerConta(id);
        return Response.noContent().build();
    }

    @POST
    @Path("{fornecedorId}/contatos")
    public Response postContatos(@PathParam("fornecedorId") Long fornecedorId, Contato contato) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = repository.buscar(fornecedorId);
        fornecedor.adicionarContato(contato);
        return Response.created(URI.create(String.format("/fornecedores/%s/contatos/%s", fornecedorId, contato.getId()))).build();
    }

    @DELETE
    @Path("{fornecedorId}/contatos/{id}")
    public Response deleteContatos(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = repository.buscar(fornecedorId);
        fornecedor.removerContato(id);
        return Response.noContent().build();
    }
}
