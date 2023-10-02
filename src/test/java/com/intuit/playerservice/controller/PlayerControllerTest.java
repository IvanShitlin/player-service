package com.intuit.playerservice.controller;

import com.intuit.playerservice.dto.PageablePlayersResultDto;
import com.intuit.playerservice.exception.PageParameterException;
import com.intuit.playerservice.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.intuit.playerservice.utils.TestUtils.*;
import static org.mockito.Mockito.when;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;

    @AfterEach
    public void tearDown() {
        Mockito.reset(playerService);
    }

    @Test
    public void testReturnPlayers() throws Exception {
        var playerDto = new PageablePlayersResultDto(List.of(generatePlayerDto()), 0, 1, 1L, 1);
        when(playerService.getAllPlayers(0, 5)).thenReturn(playerDto);
        mockMvc.perform(MockMvcRequestBuilders.get(Parameters.API_PATH)
                        .queryParam(PAGE, "0")
                        .queryParam(SIZE, "5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNumber").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1));
    }

    @Test
    public void testPageParameterException() throws Exception {
        when(playerService.getAllPlayers(-1, 5)).thenThrow(new PageParameterException(ERROR_MESSAGE));
        mockMvc.perform(MockMvcRequestBuilders.get(Parameters.API_PATH)
                        .queryParam(PAGE, "-1")
                        .queryParam(SIZE, "5"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ERROR_MESSAGE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"));
    }

    @Test
    public void testReturnPlayer() throws Exception {
        when(playerService.getPlayer(PLAYER_ID)).thenReturn(generatePlayerDto());

        mockMvc.perform(MockMvcRequestBuilders.get(Parameters.API_PATH + Parameters.PLAYER_ID_PATH, PLAYER_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerID").value(PLAYER_ID));
    }

    @Test
    public void testEntityNotFoundException() throws Exception {
        when(playerService.getPlayer(PLAYER_ID)).thenThrow(new EntityNotFoundException(ERROR_MESSAGE));

        mockMvc.perform(MockMvcRequestBuilders.get(Parameters.API_PATH + Parameters.PLAYER_ID_PATH, PLAYER_ID))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ERROR_MESSAGE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"));
    }

}
