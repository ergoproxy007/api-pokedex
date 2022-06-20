package com.dtorres.api.pokedex.testdatabuilder;

import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonSpritesResponse;

import java.util.Collections;

public class TestDataBuilderPokemonResponse {

    private PokemonResponse pokemonResponse;

    public TestDataBuilderPokemonResponse() {
        this.pokemonResponse = new PokemonResponse();
        this.pokemonResponse.setId(1L);
        this.pokemonResponse.setSprites(new PokemonSpritesResponse());
    }

    public TestDataBuilderPokemonResponse withName(String name) {
        this.pokemonResponse.setName(name);
        return this;
    }

    public TestDataBuilderPokemonResponse withWeight(Integer weight) {
        this.pokemonResponse.setWeight(weight);
        return this;
    }

    public TestDataBuilderPokemonResponse withAbility(String name) {
        this.pokemonResponse.setAbilities(Collections.singletonList(
                new TestDataBuilderPokemonAbilityResponse().withAbility(name).build()
        ));
        return this;
    }

    public TestDataBuilderPokemonResponse withType(String name) {
        this.pokemonResponse.setTypes(Collections.singletonList(
                new TestDataBuilderPokemonTypeResponse().withType(name).build()
        ));
        return this;
    }

    public PokemonResponse build() {
        return pokemonResponse;
    }
}
