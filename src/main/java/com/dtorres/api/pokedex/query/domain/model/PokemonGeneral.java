package com.dtorres.api.pokedex.query.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonGeneral {

    private Long id;
    private String name;
    private Integer weight;
}
