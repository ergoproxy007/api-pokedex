package com.dtorres.api.pokedex.query.infrastructure.repository;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;

import java.util.concurrent.CompletionStage;

public interface PokedexRepository {

    CompletionStage<PokemonGeneral> findByName(String name);
}
