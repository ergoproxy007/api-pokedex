package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model;

import com.dtorres.api.pokedex.query.infrastructure.rest.props.NameProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonAbilityDetailsResponse {

    private Long id;
    @JsonProperty("effect_entries")
    private List<Effect> effectDetails;

    @Data
    public static class Effect {
        private String effect;
        private NameProperty language;

        public boolean returnOnlyInEnglish() {
            return "en".equalsIgnoreCase(this.language.getName());
        }
    }
}
