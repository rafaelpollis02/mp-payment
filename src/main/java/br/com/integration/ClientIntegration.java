package br.com.integration;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8094")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientIntegration {

    @GET
    @Path("/api/v1/client/{id}")
    Response getScoreClientById(@PathParam("id") int id);
}
