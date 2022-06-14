package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.repository.PokedexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletionStage;

@Slf4j
@Service
public class RestClientPokedexService implements PokedexRepository {

    private final RestTemplate restTemplate;
    private final String pokeapiUrl;

    @Autowired
    public RestClientPokedexService(RestTemplateBuilder builder,
                                    @Value("${pokeapi.url}") String pokeapiUrl) {
        this.restTemplate = builder.build();
        this.pokeapiUrl = pokeapiUrl;
    }

    @Override
    public CompletionStage<PokemonGeneral> findByName(String name) {
        String finalUrl = pokeapiUrl.concat(name);
        ResponseEntity<PokemonGeneral> result = getPokemon(finalUrl);
        if (result == null || result.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return supplyAsync(() -> result.getBody());
    }

    private ResponseEntity<PokemonGeneral> getPokemon(String url) {
        try {
            return restTemplate.getForEntity(url, PokemonGeneral.class);
        } catch (HttpServerErrorException e) {
        } catch (HttpClientErrorException e) {
        }
        return null;
    }
}
