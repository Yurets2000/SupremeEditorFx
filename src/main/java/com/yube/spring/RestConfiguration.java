package com.yube.spring;

import com.yube.rest.JsonAdapter;
import com.yube.rest.RestClient;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RestConfiguration {

    @Bean(name = "restClient")
    public RestClient getRestClient() {
        return new RestClient();
    }

    @Bean(name = "jsonAdapter")
    public JsonAdapter getJsonAdapter() {
        return new JsonAdapter();
    }
}
