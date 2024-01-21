package br.com.resource;

import br.com.domain.StatusPedido;
import br.com.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @PUT
    @Path("/aprovedPedido/{id}")
    public void aprovedPedidoByClientId(@PathParam("id") int id){
        pedidoService.aprovedPedidoByClientId(id);
    }

    @PUT
    @Path("/reprovedPedido/{id}")
    public void reprovedPedidoByClientId(@PathParam("id") int id){
        pedidoService.reprovedPedidoByClientId(id);
    }

    @GET
    @Path("/getPedido")
    public Response getPedidoByStatus(@QueryParam("statusPedido") String statusPedido){
        return pedidoService.getAllPedidosByStatus("CREATE");
    }
}
