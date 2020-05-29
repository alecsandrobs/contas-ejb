package com.stolk.alecsandro.obra.exception;

import com.stolk.alecsandro.obra.dto.MensagemErroDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(MensagemErroDto.build(e.getMensagens()))
                .build();
    }
}
