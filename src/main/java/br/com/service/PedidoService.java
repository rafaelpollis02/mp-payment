package br.com.service;

import br.com.integration.PedidoIntegration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PedidoService {

    @Inject
    @RestClient
    PedidoIntegration pedidoIntegration;

    public void aprovedPedidoByClientId(int id){
        pedidoIntegration.aprovedPedidoById(id);
    }

    public void reprovedPedidoByClientId(int id){
        pedidoIntegration.reprovedPedidoById(id);
    }

    public Response getAllPedidosByStatus(String statusPedido){
     return pedidoIntegration.getProductByStatus(statusPedido);
    }

}
