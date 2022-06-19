package com.dtorres.api.pokedex.query.infrastructure.utils;

import com.dtorres.api.pokedex.query.domain.model.PokemonAbility;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonTypeResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PokedexAssembler {
    public static PokemonGeneral convertToPokemonGeneral(PokemonResponse response) {
        List<PokemonTypeResponse.Type> types = response.getTypes()
                                                       .stream()
                                                       .map(PokemonTypeResponse::getType)
                                                       .collect(Collectors.toList());

        List<PokemonAbility> abilities = response.getAbilities()
                                                 .stream()
                                                 .map(ability -> PokemonAbility.builder().name(ability.getAbility().getName()).description(ability.getDescription()).build())
                                                 .collect(Collectors.toList());
        return PokemonGeneral.create(
                response.getId(),
                response.getName(),
                response.getWeight(),
                response.getSprites().getFrontDefault() != null ? response.getSprites().getFrontDefault() : response.getSprites().getBackDefault(),
                types.stream().map(PokemonTypeResponse.Type::getName).collect(Collectors.toList()),
                abilities
        );
    }
}
