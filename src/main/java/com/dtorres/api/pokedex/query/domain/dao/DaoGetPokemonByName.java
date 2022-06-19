package com.dtorres.api.pokedex.query.domain.dao;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;

import java.util.Map;
import java.util.concurrent.CompletionStage;

public interface DaoGetPokemonByName {
    CompletionStage<PokemonGeneral> findByName(String name, Map<String, String> queryParams);
}
