package com.dtorres.api.pokedex.commons.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ResponseDTO<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public void fail () {
        this.success = false;
        this.code = ResponseConstants.ERROR_CODE.toString();
    }

    public void success () {
        this.success = true;
        this.code = ResponseConstants.SUCCESS_CODE.toString();
    }

    public static ResponseDTO failed() {
        ResponseDTO respuesta = new ResponseDTO();
        respuesta.fail();
        return respuesta;
    }
}
