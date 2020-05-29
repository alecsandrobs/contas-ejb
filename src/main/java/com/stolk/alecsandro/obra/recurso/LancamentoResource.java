package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.modelo.Lancamento;
import com.stolk.alecsandro.obra.repository.LancamentoRepository;
import com.stolk.alecsandro.obra.service.LancamentoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.PAGAMENTO;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.RECEBIMENTO;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Stateless
@Path("lancamentos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class LancamentoResource implements Serializable {

    @Inject
    private LancamentoRepository repository;

    @Inject
    private LancamentoService service;

    @GET
    public Response get() {
        List<Lancamento> lancamentos = repository.buscar();
        return Response.ok(lancamentos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Lancamento lancamento = new Lancamento();
        lancamento = repository.buscar(id);
        return Response.ok(lancamento).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = repository.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @GET
    @Path("pagamentos")
    public Response getPagamentos() {
        Double total = repository.buscarSoma(PAGAMENTO, false);
        return Response.ok(total).build();
    }

    @GET
    @Path("pago")
    public Response getPagamentosPago() {
        Double total = repository.buscarSoma(PAGAMENTO, true);
        return Response.ok(total).build();
    }

    @GET
    @Path("recebimentos")
    public Response getRecebimentos() {
        Double total = repository.buscarSoma(RECEBIMENTO, false);
        return Response.ok(total).build();
    }

    @GET
    @Path("recebido")
    public Response getRecebimentosRecebido() {
        Double total = repository.buscarSoma(RECEBIMENTO, true);
        return Response.ok(total).build();
    }

    @POST
    public Response post(Lancamento lancamento) {
        service.cadastrar(lancamento);
        return Response.created(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @POST
    @Path("lista")
    public Response posts(List<Lancamento> lancamentos) {
        lancamentos.stream().forEach(lancamento -> service.cadastrar(lancamento));
        return Response.status(CREATED).build();
    }

    @PUT
    public Response put(Lancamento lancamento) {
        service.editar(lancamento);
        return Response.ok(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Lancamento lancamento) {
        service.editar(id, lancamento);
        return Response.ok(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @DELETE
    public Response delete(Lancamento lancamento) {
        service.excluir(lancamento);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}
