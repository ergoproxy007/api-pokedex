package com.dtorres.api.pokedex.query.infrastructure.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.OK;

import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import com.dtorres.api.pokedex.query.application.handler.HandlerPokemonFindByName;
import com.dtorres.api.pokedex.query.domain.dao.DaoGetPokemonByName;
import com.dtorres.api.pokedex.query.infrastructure.repository.PokedexRepository;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.RestClientPokedexService;
import com.dtorres.api.pokedex.testdatabuilder.TestDataBuilderPokemonResponse;
import com.dtorres.api.pokedex.commons.CommonUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PokemonQueryControllerTest extends CommonUnitTest {

    private PokemonQueryController controller;
    private HandlerPokemonFindByName handlerPokemonFindByName;
    private DaoGetPokemonByName daoGetPokemonByName;

    @Mock
    private RestClientPokedexService service;

    @BeforeEach
    public void init() {
        this.daoGetPokemonByName = new PokedexRepository(service);
        this.handlerPokemonFindByName = new HandlerPokemonFindByName(this.daoGetPokemonByName);
        this.controller = new PokemonQueryController(this.handlerPokemonFindByName);
    }

    @Test
    public void findByNameTest() {
        //Arrange
        String pokemonName = "pikachu";
        String queryAbility = "N";
        //Mock
        when(service.findByName(any(), any()))
                    .thenReturn(new TestDataBuilderPokemonResponse()
                        .withName(pokemonName)
                        .withWeight(30)
                        .withAbility("imposter")
                        .withType("normal")
                        .build());
        //Act
        ResponseEntity<ResponseDTO> result = this.controller.findByName(pokemonName, queryAbility);
        //Assert
        assertEquals(OK, result.getStatusCode());
        assertNotNull(getAsPokemonGeneral(result).getType());
        assertTrue(getAsPokemonGeneral(result).getAbilities().stream().findFirst().isPresent());
        assertEquals("normal", getAsPokemonGeneral(result).getType(),"error comparing pokemon type");
        assertNotEquals("invisibility", getAsPokemonGeneral(result).getAbilities().get(0).getName(),"this is not an ability");
        verify(service, times(1)).findByName(any(), any());
    }

}
