package com.dtorres.api.pokedex.testdatabuilder;

import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityResponse;

public class TestDataBuilderPokemonAbilityResponse {

    private PokemonAbilityResponse.Ability ability;

    public TestDataBuilderPokemonAbilityResponse() {
        this.ability = new PokemonAbilityResponse.Ability();
    }

    public TestDataBuilderPokemonAbilityResponse withAbility(String abilityName) {
        this.ability = new PokemonAbilityResponse.Ability();
        ability.setName(abilityName);
        return this;
    }

    public PokemonAbilityResponse build() {
        PokemonAbilityResponse pokemonAbilityResponse = new PokemonAbilityResponse();
        pokemonAbilityResponse.setAbility(ability);
        return pokemonAbilityResponse;
    }
}
