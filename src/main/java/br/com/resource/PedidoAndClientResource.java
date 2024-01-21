package br.com.resource;

import br.com.exception.BusinessException;
import br.com.service.ValidatePedidoAndClient;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/api/v1/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PedidoAndClientResource {

    @Inject
    ValidatePedidoAndClient validatePedidoAndClient;

    @GET
    @Path("/validate")
    @Scheduled(every = "30s")
    public void validatePedidoAndClient() throws BusinessException {
        System.out.println("Scheduled executado em : " + LocalDateTime.now());
        validatePedidoAndClient.validatePedidoAndClient();
    }

}
