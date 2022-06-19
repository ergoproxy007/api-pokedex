package com.dtorres.api.pokedex.query.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonGeneral {

    private Long id;
    private String name;
    private Integer weight;
    private String type;
    private String image;
    private List<PokemonAbility> abilities;

    public static PokemonGeneral create(Long id, String name, Integer weight, String image, List<String> types, List<PokemonAbility> abilities) {
        return PokemonGeneral.builder()
                .id(id)
                .name(name)
                .weight(weight)
                .image(image)
                .type(String.join(",",types))
                .abilities(abilities)
                .build();
    }
}
