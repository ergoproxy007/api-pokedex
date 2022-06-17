package com.dtorres.api.pokedex.query.application.handler;

import static com.dtorres.api.pokedex.query.infrastructure.exception.helper.QueryExceptionHelper.throwObject;

import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import com.dtorres.api.pokedex.query.domain.dao.DaoGetPokemonByName;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
public class HandlerPokemonFindByName {

    private final DaoGetPokemonByName daoGetPokemonByName;

    @Autowired
    public HandlerPokemonFindByName(DaoGetPokemonByName daoGetPokemonByName) {
        this.daoGetPokemonByName = daoGetPokemonByName;
    }

    public ResponseDTO execute(String name) {
        CompletionStage<PokemonGeneral> promise = daoGetPokemonByName.findByName(name);
        ResponseDTO response = new ResponseDTO();
        response.setData(promise.exceptionally(throwable -> throwObject(throwable))
                                .toCompletableFuture().join());
        response.success();
        return response;
    }
}
