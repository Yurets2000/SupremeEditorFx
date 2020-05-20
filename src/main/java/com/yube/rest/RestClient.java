package com.yube.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.client.utils.URIBuilder;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.util.HashMap;

public class RestClient {

    private final Client client = new Client();

    public String getRequest(String url, HashMap<String, String> params) {
        URIBuilder builder;
        WebResource webResource;
        try {
            builder = new URIBuilder(url);
            if(params != null) {
                params.forEach(builder::addParameter);
            }
            webResource = client.resource(builder.build());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Exception during URI construct", e);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus() != 200) {
            throw new RuntimeException("HTTP Error: " + response.getStatus());
        }
        return response.getEntity(String.class);
    }
}
