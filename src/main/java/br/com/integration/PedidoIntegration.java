package br.com.integration;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8092")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PedidoIntegration {

    @PUT
    @Path("/api/v1/pedido/{id}/aprovedPedido")
    Response aprovedPedidoById(@PathParam("id") int id);

    @PUT
    @Path("/api/v1/pedido/{id}/reprovedPedido")
    Response reprovedPedidoById(@PathParam("id") int id);

    @GET
    @Path("/api/v1/pedido/status")
    Response getProductByStatus(@QueryParam("statusPedido") String statusPedido);

}
