package com.intuit.playerservice.service;

import com.intuit.playerservice.dto.PageablePlayersResultDto;
import com.intuit.playerservice.repository.PlayerRepository;
import com.intuit.playerservice.dto.PlayerDto;
import com.intuit.playerservice.mapper.PlayerMapper;
import com.intuit.playerservice.service.validator.PageValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static final Supplier<EntityNotFoundException> ENTITY_NOT_FOUND_EXCEPTION_SUPPLIER =
            () -> new EntityNotFoundException("Player not found!");
    private final PageValidator pageValidator;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PageablePlayersResultDto getAllPlayers(int page, int size) {
        pageValidator.validateRequest(page, size);
        var pageRequest = PageRequest.of(page, size);
        var playerDtoPage = playerRepository.findAll(pageRequest)
                .map(playerMapper::toDto);

        return new PageablePlayersResultDto(
                playerDtoPage.getContent(),
                playerDtoPage.getNumber(),
                playerDtoPage.getNumberOfElements(),
                playerDtoPage.getTotalElements(),
                playerDtoPage.getTotalPages()
        );
    }

    public PlayerDto getPlayer(String playerID) {
        return playerRepository.findById(playerID)
                .map(playerMapper::toDto)
                .orElseThrow(ENTITY_NOT_FOUND_EXCEPTION_SUPPLIER);
    }

}
