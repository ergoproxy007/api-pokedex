package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model;

import com.dtorres.api.pokedex.query.infrastructure.rest.props.NameProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTypeResponse {

    private String slot;
    private Type type;

    @Data
    public static class Type extends NameProperty {
    }
}
