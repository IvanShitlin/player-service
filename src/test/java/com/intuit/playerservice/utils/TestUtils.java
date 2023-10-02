package com.intuit.playerservice.utils;

import com.intuit.playerservice.domain.Player;
import com.intuit.playerservice.dto.PlayerDto;

import java.time.LocalDate;

public class TestUtils {

    public static final String PLAYER_ID = "1";
    public static final String ERROR_MESSAGE = "Error message";
    public static final String PAGE = "page";
    public static final String SIZE = "size";

    public static PlayerDto generatePlayerDto() {
        return new PlayerDto(PLAYER_ID,
                1980,
                1,
                1,
                "USA",
                "WashingtonDC",
                "Washington",
                2000,
                1,
                1,
                "USA",
                "WashingtonDC",
                "Washington",
                "John",
                "Johnson",
                "Johnny",
                90,
                190,
                "Bats",
                "Throws",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2000, 12, 31),
                "retroID",
                "bbrefID"
        );
    }

    public static Player generatePlayer() {
        return new Player(PLAYER_ID,
                1980,
                1,
                1,
                "USA",
                "WashingtonDC",
                "Washington",
                2000,
                1,
                1,
                "USA",
                "WashingtonDC",
                "Washington",
                "John",
                "Johnson",
                "Johnny",
                90,
                190,
                "Bats",
                "Throws",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2000, 12, 31),
                "retroID",
                "bbrefID");
    }

}
