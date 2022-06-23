package com.dtorres.api.pokedex.testdatabuilder;

import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityDetailsResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.props.NameProperty;

import java.util.Collections;

public class TestDataBuilderPokemonAbilityDetailsResponse {

    private PokemonAbilityDetailsResponse details;

    public TestDataBuilderPokemonAbilityDetailsResponse() {
        this.details = new PokemonAbilityDetailsResponse();
    }

    public TestDataBuilderPokemonAbilityDetailsResponse withEffect(String description) {
        PokemonAbilityDetailsResponse.Effect effect = new PokemonAbilityDetailsResponse.Effect();
        effect.setEffect(description);
        effect.setLanguage(NameProperty.builder().name("EN").build());
        details.setEffectDetails(Collections.singletonList(effect));
        return this;
    }

    public PokemonAbilityDetailsResponse build() {
        return details;
    }
}
