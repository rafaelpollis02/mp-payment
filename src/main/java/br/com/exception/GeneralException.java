package br.com.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class GeneralException implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {

        MessageError messageError = new MessageError();

        if (e instanceof BusinessException) {
            messageError.setMessage(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        } else {
            messageError.setMessage("Obtivemos uma falha, entre em contato com o suporte!");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageError).build();
        }
    }

}
