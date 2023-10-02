package com.intuit.playerservice.controller;

import com.intuit.playerservice.dto.PageablePlayersResultDto;
import com.intuit.playerservice.dto.PlayerDto;
import com.intuit.playerservice.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.intuit.playerservice.controller.Parameters.*;

@Tag(name = "Players Service API", description = "Operations pertaining to players in Players Service")
@RestController
@RequestMapping(API_PATH)
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(PLAYER_ID_PATH)
    @Operation(summary = "Get player by ID")
    public PlayerDto getPlayer(@PathVariable(PLAYER_ID_PARAM) String playerID) {
        return playerService.getPlayer(playerID);
    }

    @GetMapping
    @Operation(summary = "Get all players")
    public PageablePlayersResultDto getAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "50") int size) {
        return playerService.getAllPlayers(page, size);
    }

}