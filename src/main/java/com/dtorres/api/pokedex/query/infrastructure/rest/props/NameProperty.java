package com.dtorres.api.pokedex.query.infrastructure.rest.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameProperty {

    private String name;
    private String url;
}
