package com.dtorres.api.pokedex.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoResponseEntityException {
    private int code;
    private String error;

    public DtoResponseEntityException(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public DtoResponseEntityException(String error) {
        this.error = error;
    }
}
