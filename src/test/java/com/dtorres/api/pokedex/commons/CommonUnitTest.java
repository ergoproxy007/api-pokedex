package com.dtorres.api.pokedex.commons;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.dtorres.api.pokedex.commons.constants.QueryConstants.GET_ABILITY;

public class CommonUnitTest {

    public PokemonGeneral getAsPokemonGeneral(ResponseEntity<ResponseDTO> result) {
        return (PokemonGeneral) result.getBody().getData();
    }

    public Map<String, String> getSimpleMap(String key, String value) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(key, value);
        return queryParams;

    }
}
