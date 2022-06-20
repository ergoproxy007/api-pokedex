package com.dtorres.api.pokedex.testdatabuilder;

import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonTypeResponse;

public class TestDataBuilderPokemonTypeResponse {

    private PokemonTypeResponse.Type type;

    public TestDataBuilderPokemonTypeResponse() {
        this.type = new PokemonTypeResponse.Type();
    }

    public TestDataBuilderPokemonTypeResponse withType(String typeName) {
        type.setName(typeName);
        return this;
    }

    public PokemonTypeResponse build() {
        PokemonTypeResponse pokemonTypeResponse = new PokemonTypeResponse();
        pokemonTypeResponse.setType(type);
        return pokemonTypeResponse;
    }
}
