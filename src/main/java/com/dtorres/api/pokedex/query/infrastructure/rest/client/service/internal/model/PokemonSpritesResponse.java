package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonSpritesResponse {

    @JsonProperty("front_default")
    private String frontDefault;
    @JsonProperty("back_default")
    private String backDefault;

}
