package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model;

import com.dtorres.api.pokedex.query.infrastructure.rest.props.NameProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonAbilityResponse {

    private String slot;
    private Ability ability;
    private String description;

    @Data
    public static class Ability extends NameProperty {
    }
}
