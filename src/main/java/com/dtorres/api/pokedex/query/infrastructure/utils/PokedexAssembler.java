package com.dtorres.api.pokedex.query.infrastructure.utils;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.rest.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.model.PokemonTypeResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PokedexAssembler {
    public static PokemonGeneral convertToPokemonGeneral(PokemonResponse response) {
        List<PokemonTypeResponse.Type> types = response.getTypes().stream()
                                                                  .map(PokemonTypeResponse::getType)
                                                                  .collect(Collectors.toList());
        return PokemonGeneral.create(
                response.getId(),
                response.getName(),
                response.getWeight(),
                types.stream().map(PokemonTypeResponse.Type::getName).collect(Collectors.toList())
        );
    }
}
