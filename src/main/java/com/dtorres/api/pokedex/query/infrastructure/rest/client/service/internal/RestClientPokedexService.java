package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal;

import com.dtorres.api.pokedex.commons.domain.exception.NotFoundDataException;
import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import com.dtorres.api.pokedex.query.infrastructure.rest.model.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

@Service
public class RestClientPokedexService {

    private static final String EMPTY_RESPONSE = "The response is empty or the find by name service is not available";

    private final RestTemplate restTemplate;
    private final String pokeapiUrl;

    @Autowired
    public RestClientPokedexService(RestTemplateBuilder builder,
                                    @Value("${pokeapi.url}") String apiUrl) {
        this.restTemplate = builder.build();
        this.pokeapiUrl = apiUrl;
    }

    public PokemonResponse findByName(String name) {
        String finalUrl = pokeapiUrl.concat(name);
        ResponseEntity<PokemonResponse> result = getPokemon(finalUrl);
        if (result == null || result.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundDataException(EMPTY_RESPONSE);
        }
        return result.getBody();
    }

    private ResponseEntity<PokemonResponse> getPokemon(String url) {
        try {
            return restTemplate.getForEntity(url, PokemonResponse.class);
        } catch (HttpServerErrorException e) {
            throw new TechnicalException(e.getMessage());
        } catch (HttpClientErrorException e) {
            throw new TechnicalException(e.getMessage());
        }
    }
}
