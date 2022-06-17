package com.dtorres.api.pokedex.query.infrastructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponse {
    private Long id;
    private String name;
    private Integer weight;
    private List<PokemonTypeResponse> types;
}
