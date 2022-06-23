package com.dtorres.api.pokedex.query.infrastructure.rest.client.operations;

import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityDetailsResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.props.NameProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
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

    private ResponseEntity<?> getResponseEntity(String url, Class<?> classType) {
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

    @Cacheable("pokemon")
    public ResponseEntity<PokemonResponse> getPokemonResponse(String url) {
        log.info("validate no cache & call service, access to Pokemon, url={}", url);
        return (ResponseEntity<PokemonResponse>) getResponseEntity(url, PokemonResponse.class);
    }

    @Cacheable("abilitydetails")
    public ResponseEntity<PokemonAbilityDetailsResponse> getPokemonAbilityDetailsResponse(String url) {
        log.info("validate no cache & call service, access to Pokemon Ability Details, url={}", url);
        return (ResponseEntity<PokemonAbilityDetailsResponse>) getResponseEntity(url, PokemonAbilityDetailsResponse.class);
    }
}
