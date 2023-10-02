package com.intuit.playerservice.service;

import com.intuit.playerservice.domain.Player;
import com.intuit.playerservice.exception.PageParameterException;
import com.intuit.playerservice.mapper.PlayerMapper;
import com.intuit.playerservice.repository.PlayerRepository;
import com.intuit.playerservice.service.validator.PageValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.intuit.playerservice.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PlayerService.class, PageValidator.class})
@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private PlayerMapper playerMapper;

    @Autowired
    private PlayerService playerService;

    @AfterEach
    public void tearDown() {
        Mockito.reset(playerMapper, playerRepository);
    }

    @Test
    void testPageParameterException() {
        assertThrows(PageParameterException.class, () -> playerService.getAllPlayers(0,0));
        assertThrows(PageParameterException.class, () -> playerService.getAllPlayers(-1,0));
        assertThrows(PageParameterException.class, () -> playerService.getAllPlayers(0,-1));
    }

    @Test
    public void testGetAllPlayers() {
        var players = List.of(generatePlayer());
        var playerPage = new PageImpl<>(players, PageRequest.of(0, 1), players.size());

        when(playerRepository.findAll(any(Pageable.class))).thenReturn(playerPage);

        var dtos = List.of(generatePlayerDto());
        when(playerMapper.toDto(any(Player.class))).thenReturn(dtos.get(0));

        var result = playerService.getAllPlayers(0, 1);

        assertEquals(dtos, result.players());
        assertEquals(0, result.pageNumber());
        assertEquals(1, result.numberOfElements());
        assertEquals(1, result.totalElements());
        assertEquals(1, result.totalPages());
    }

    @Test
    public void testGetPlayer() {
        var player = generatePlayer();

        when(playerRepository.findById(eq(PLAYER_ID))).thenReturn(Optional.of(player));

        var playerDto = generatePlayerDto();
        when(playerMapper.toDto(any(Player.class))).thenReturn(playerDto);

        var result = playerService.getPlayer(PLAYER_ID);

        assertEquals(playerDto, result);
    }

    @Test
    public void testEntityNotFoundException() {
        when(playerRepository.findById(eq(PLAYER_ID))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> playerService.getPlayer(PLAYER_ID));
    }

}
