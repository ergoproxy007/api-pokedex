package com.dtorres.api.pokedex.query.infrastructure.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.api.pokedex.query.domain.dao.DaoGetPokemonByName;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.RestClientPokedexService;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.utils.PokedexAssembler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletionStage;

@Component
public class PokedexRepository implements DaoGetPokemonByName {

    private final RestClientPokedexService service;

    public PokedexRepository(RestClientPokedexService service) {
        this.service = service;
    }

    @Override
    public CompletionStage<PokemonGeneral> findByName(String name, Map<String, String> queryParams) {
        PokemonResponse response = service.findByName(name, queryParams);
        return supplyAsync(() -> PokedexAssembler.convertToPokemonGeneral(response));
    }

}
