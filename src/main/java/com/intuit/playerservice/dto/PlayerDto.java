package com.intuit.playerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PlayerDto(
        String playerID,
        Integer birthYear,
        Integer birthMonth,
        Integer birthDay,
        String birthCountry,
        String birthState,
        String birthCity,
        Integer deathYear,
        Integer deathMonth,
        Integer deathDay,
        String deathCountry,
        String deathState,
        String deathCity,
        String nameFirst,
        String nameLast,
        String nameGiven,
        Integer weight,
        Integer height,
        String bats,
        @JsonProperty("throws") String _throws,
        LocalDate debut,
        LocalDate finalGame,
        String retroID,
        String bbrefID
) {
}

