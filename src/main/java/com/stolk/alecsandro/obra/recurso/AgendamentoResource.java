package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.exception.BusinessException;
import com.stolk.alecsandro.obra.modelo.Agendamento;
import com.stolk.alecsandro.obra.repository.AgendamentoRepository;
import com.stolk.alecsandro.obra.service.AgendamentoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("agendamentos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class AgendamentoResource implements Serializable {

    @Inject
    private AgendamentoRepository repository;

    @Inject
    private AgendamentoService service;

    @GET
    public Response buscar() {
        List<Agendamento> emails = repository.buscar();
        return Response.ok(emails).build();
    }

    @POST
    public Response cadastrar(Agendamento agendamento) throws BusinessException {
        service.cadastrar(agendamento);
        return Response.created(URI.create(String.format("/agendamentos/%s", agendamento.getId()))).build();
    }
}
