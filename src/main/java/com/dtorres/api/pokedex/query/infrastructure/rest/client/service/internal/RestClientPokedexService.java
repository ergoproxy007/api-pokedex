package com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal;

import static com.dtorres.api.pokedex.commons.constants.QueryConstants.GET_ABILITY;
import static com.dtorres.api.pokedex.commons.constants.QueryConstants.YES;

import com.dtorres.api.pokedex.commons.domain.exception.NotFoundDataException;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.operations.RestTemplateOperations;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityDetailsResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Service
public class RestClientPokedexService {

    private static final String EMPTY_RESPONSE = "The response is empty or the find by name service is not available";

    private final RestTemplateOperations restTemplateOperations;
    private final String pokeapiUrl;

    @Autowired
    public RestClientPokedexService(RestTemplateOperations restTemplateOperations,
                                    @Value("${pokeapi.url}") String apiUrl) {
        this.restTemplateOperations = restTemplateOperations;
        this.pokeapiUrl = apiUrl;
    }

    public PokemonResponse findByName(String name, Map<String, String> queryParams) {
        String finalUrl = pokeapiUrl.concat(name);
        ResponseEntity<PokemonResponse> result = restTemplateOperations.getPokemonResponse(finalUrl);
        if (result == null || result.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundDataException(EMPTY_RESPONSE);
        }
        PokemonResponse pokemon = result.getBody();
        this.setDescriptionAbility(pokemon.getAbilities(), queryParams.get(GET_ABILITY));
        return pokemon;
    }

    public PokemonAbilityDetailsResponse getPokemonAbilityDetails(PokemonAbilityResponse.Ability ability) {
        ResponseEntity<PokemonAbilityDetailsResponse> result = restTemplateOperations.getPokemonAbilityDetailsResponse(ability.getUrl());
        if (result == null || result.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundDataException(EMPTY_RESPONSE);
        }
        return result.getBody();
    }

    private void setDescriptionAbility(List<PokemonAbilityResponse> abilities, String withAbility) {
        if (YES.equalsIgnoreCase(withAbility)) {
            abilities.parallelStream().forEach(ability -> {
                PokemonAbilityDetailsResponse pokemonAbilityDetails = getPokemonAbilityDetails(ability.getAbility());

                PokemonAbilityDetailsResponse.Effect effect = pokemonAbilityDetails.getEffectDetails().stream()
                        .filter(PokemonAbilityDetailsResponse.Effect::returnOnlyInEnglish)
                        .findFirst().orElse(new PokemonAbilityDetailsResponse.Effect());

                ability.setDescription(effect.getEffect());
            });
        }
    }

}
