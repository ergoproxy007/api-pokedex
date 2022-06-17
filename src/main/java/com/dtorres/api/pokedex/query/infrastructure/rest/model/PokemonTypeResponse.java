package com.dtorres.api.pokedex.query.infrastructure.rest.model;

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
    public static class Type {
        private String name;
        private String url;
    }
}
