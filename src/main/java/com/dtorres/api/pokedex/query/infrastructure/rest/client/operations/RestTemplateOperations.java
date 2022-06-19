package com.dtorres.api.pokedex.query.infrastructure.rest.client.operations;

import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateOperations {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateOperations(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseEntity<?> getResponseEntity(String url, Class<?> classType) {
        try {
            return restTemplate.getForEntity(url, classType);
        } catch (HttpServerErrorException e) {
            log.error("error trying to access resource, url={}, statusCode={}", url, e.getStatusCode(), e);
            throw new TechnicalException(e.getMessage());
        } catch (HttpClientErrorException e) {
            log.error("error trying to access resource, url={}, statusCode={}", url, e.getStatusCode(), e);
            throw new TechnicalException(e.getMessage());
        }
    }
}
