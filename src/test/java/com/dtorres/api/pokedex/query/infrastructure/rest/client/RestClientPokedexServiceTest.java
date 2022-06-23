package com.dtorres.api.pokedex.query.infrastructure.rest.client;


import com.dtorres.api.pokedex.commons.CommonUnitTest;
import com.dtorres.api.pokedex.query.application.handler.HandlerPokemonFindByName;
import com.dtorres.api.pokedex.query.infrastructure.controller.PokemonQueryController;
import com.dtorres.api.pokedex.query.infrastructure.repository.PokedexRepository;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.operations.RestTemplateOperations;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.RestClientPokedexService;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonAbilityDetailsResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.model.PokemonResponse;
import com.dtorres.api.pokedex.testdatabuilder.TestDataBuilderPokemonAbilityDetailsResponse;
import com.dtorres.api.pokedex.testdatabuilder.TestDataBuilderPokemonResponse;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestClientPokedexServiceTest extends CommonUnitTest {

    private final static String URL = "https://pokeapi.co/api/v2/pokemon/";
    private final static String DESCRIPTION = "\"Forms have different stats. If Aegislash's ability is stance change, it changes to Blade Forme before using a damaging move, and reverts to Shield Forme before using King's Shield or upon leaving the field.";

    @InjectMocks
    private RestClientPokedexService restClientPokedexService;

    @Mock
    private RestTemplateOperations restTemplate;

    @BeforeEach
    public void init() {
        this.restClientPokedexService = new RestClientPokedexService(restTemplate, URL);
    }

    @Test
    public void findByNameWithoutAbilityTest() {
        //Arrange
        PokemonResponse squirtle = new TestDataBuilderPokemonResponse().withName("squirtle")
                                        .withWeight(10).withAbility("runner").withType("water").build();
        doReturn(ResponseEntity.ok(squirtle)).when(restTemplate).getPokemonResponse(any());
        //Act
        PokemonResponse squirtleResponse = this.restClientPokedexService.findByName("squirtle", getSimpleMap("GA", "N"));
        //Assert
        assertNotNull(squirtleResponse);
        assertEquals("water", squirtleResponse.getTypes().stream().findFirst().get().getType().getName(), "Pokemom type is not valid");
        verify(restTemplate, times(1)).getPokemonResponse(any());
    }

    @Test
    public void findByNameGetAbilityTest() {
        //Arrange
        PokemonResponse squirtle = new TestDataBuilderPokemonResponse().withName("squirtle")
                .withWeight(10).withAbility("runner").withType("water").build();
        doReturn(ResponseEntity.ok(squirtle)).when(restTemplate).getPokemonResponse(any());

        PokemonAbilityDetailsResponse description = new TestDataBuilderPokemonAbilityDetailsResponse().withEffect(DESCRIPTION).build();
        doReturn(ResponseEntity.ok(description)).when(restTemplate).getPokemonAbilityDetailsResponse(any());
        //Act
        PokemonResponse squirtleResponse = this.restClientPokedexService.findByName("squirtle", getSimpleMap("GA", "Y"));
        assertNotNull(squirtleResponse);
        assertEquals(DESCRIPTION, squirtleResponse.getAbilities().stream().findFirst().get().getDescription(), "Invalid Descriptipn");

        verify(restTemplate, times(1)).getPokemonResponse(any());
        verify(restTemplate, times(1)).getPokemonAbilityDetailsResponse(any());
    }
}
