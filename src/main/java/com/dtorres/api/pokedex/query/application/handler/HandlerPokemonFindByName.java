package com.dtorres.api.pokedex.query.application.handler;

import static com.dtorres.api.pokedex.commons.constants.QueryConstants.GET_ABILITY;
import static com.dtorres.api.pokedex.commons.domain.exception.helper.ExceptionHelper.throwObject;

import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import com.dtorres.api.pokedex.query.domain.dao.DaoGetPokemonByName;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@Component
public class HandlerPokemonFindByName {

    private final DaoGetPokemonByName daoGetPokemonByName;

    @Autowired
    public HandlerPokemonFindByName(DaoGetPokemonByName daoGetPokemonByName) {
        this.daoGetPokemonByName = daoGetPokemonByName;
    }

    public ResponseDTO execute(String name, String queryAbility) {
        CompletionStage<PokemonGeneral> promiseByName = daoGetPokemonByName.findByName(name, getQueryParams(queryAbility));
        ResponseDTO response = new ResponseDTO();
        response.setData(promiseByName.exceptionally(throwable -> throwObject(throwable))
                                .toCompletableFuture().join());
        response.success();
        return response;
    }

    private Map<String, String> getQueryParams(String queryAbility) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(GET_ABILITY, queryAbility);
        return queryParams;
    }
}
