package br.com.service;

import br.com.exception.BusinessException;
import br.com.integration.ClientIntegration;
import br.com.integration.PedidoIntegration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ValidatePedidoAndClient {

    @Inject
    @RestClient
    ClientIntegration clientIntegration;

    @Inject
    @RestClient
    PedidoIntegration pedidoIntegration;

    public void validatePedidoAndClient() throws BusinessException {

        Response pedidoResponse = pedidoIntegration.getProductByStatus("CREATE");

        try {
            if (pedidoResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                JsonArray jsonResponseArray = pedidoResponse.readEntity(JsonArray.class);

                for (JsonValue jsonValue : jsonResponseArray) {
                    if (jsonValue instanceof JsonObject) {

                        JsonObject jsonObject = (JsonObject) jsonValue;

                        int idProduct = jsonObject.getInt("idProduct");
                        int idPedido = jsonObject.getInt("id");
                        int idClient = jsonObject.getInt("idClient");
                        String statusPedido = jsonObject.getString("statusPedido");

                        Response clientResponse = clientIntegration.getScoreClientById(idClient);

                        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                            JsonObject jsonResponse = clientResponse.readEntity(JsonObject.class);
                            int score = jsonResponse.getInt("score");

                            System.out.println("cliente: " + idClient + " score: " + score + " pedido: " + idPedido + " produto: " + idProduct + " statusPedido: " + statusPedido);

                            if (score <= 3) {
                                pedidoIntegration.reprovedPedidoById(idPedido);
                            } else {
                                pedidoIntegration.aprovedPedidoById(idPedido);
                            }
                        }
                    }
                }

            } else {
                System.out.println(pedidoResponse.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pedidoResponse.close();

        }

    }
}
