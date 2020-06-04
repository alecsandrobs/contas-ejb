package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.repository.TarefaRepository;
import com.stolk.alecsandro.obra.service.TarefaService;

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
@Path("tarefas")
@Consumes({APPLICATION_JSON, APPLICATION_XML})
@Produces({APPLICATION_JSON, APPLICATION_XML})
public class TarefaResource implements Serializable {

    @Inject
    private TarefaRepository repository;

    @Inject
    private TarefaService service;

    @GET
    public Response get() {
        List<Tarefa> tarefas = repository.buscar();
        return Response.ok(tarefas).build();
    }

    @GET
    @Path("fazer")
    public Response getFazer() {
        List<Tarefa> tarefas = repository.buscarNaoFeitos();
        return Response.ok(tarefas).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Tarefa tarefa = repository.buscar(id);
        return Response.ok(tarefa).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = repository.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    public Response post(Tarefa tarefa) {
        service.cadastrar(tarefa);
        return Response.created(URI.create(String.format("/tarefas/%s", tarefa.getId()))).build();
    }

    @POST
    @Path("lista")
    public Response posts(List<Tarefa> tarefas) {
        tarefas.stream().forEach(tarefa -> service.cadastrar(tarefa));
        return Response.status(CREATED).build();
    }

    @PUT
    public Response put(Tarefa tarefa) {
        service.editar(tarefa);
        return Response.ok(URI.create(String.format("/tarefas/%s", tarefa.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Tarefa tarefa) {
        service.editar(id, tarefa);
        return Response.ok(URI.create(String.format("/tarefas/%s", tarefa.getId()))).build();
    }

    @PUT
    @Path("{id}/feito")
    public Response putFeito(@PathParam("id") Long id) {
        Tarefa tarefa = repository.buscar(id);
        service.marcarFeito(tarefa);
        return Response.ok(URI.create(String.format("/tarefas/%s", tarefa.getId()))).build();
    }

    @DELETE
    public Response delete(Tarefa tarefa) {
        service.excluir(tarefa);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}
