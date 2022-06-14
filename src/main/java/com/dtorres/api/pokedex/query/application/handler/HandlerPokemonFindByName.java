package com.dtorres.api.pokedex.query.application.handler;

import static com.dtorres.api.pokedex.query.infrastructure.exception.helper.QueryExceptionHelper.throwObject;

import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
public class HandlerPokemonFindByName {

    private final PokedexRepository pokedexRepository;

    @Autowired
    public HandlerPokemonFindByName(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    public ResponseDTO execute(String name) {
        CompletionStage<PokemonGeneral> promise = pokedexRepository.findByName(name);
        ResponseDTO response = new ResponseDTO();
        response.success();
        response.setData(promise.exceptionally(throwable -> throwObject(throwable))
                                .toCompletableFuture().join());
        return response;
    }
}
