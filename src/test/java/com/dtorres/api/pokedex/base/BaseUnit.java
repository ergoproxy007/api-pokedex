package com.dtorres.api.pokedex.base;

import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class BaseUnit {

    public PokemonGeneral getAsPokemonGeneral(ResponseEntity<ResponseDTO> result) {
        return (PokemonGeneral) result.getBody().getData();
    }
}
