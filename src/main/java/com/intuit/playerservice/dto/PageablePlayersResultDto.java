package com.intuit.playerservice.dto;

import java.util.List;

public record PageablePlayersResultDto (
        List<PlayerDto> players,
        int pageNumber,
        int numberOfElements,
        long totalElements,
        int totalPages
) {}
