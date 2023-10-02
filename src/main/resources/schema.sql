DROP TABLE IF EXISTS player;

CREATE TABLE player (
                        player_id VARCHAR(255) PRIMARY KEY,
                        birth_year INT,
                        birth_month INT,
                        birth_day INT,
                        birth_country VARCHAR(255),
                        birth_state VARCHAR(255),
                        birth_city VARCHAR(255),
                        death_year INT,
                        death_month INT,
                        death_day INT,
                        death_country VARCHAR(255),
                        death_state VARCHAR(255),
                        death_city VARCHAR(255),
                        name_first VARCHAR(255),
                        name_last VARCHAR(255),
                        name_given VARCHAR(255),
                        weight INT,
                        height INT,
                        bats VARCHAR(255),
                        throws VARCHAR(255),
                        debut DATE,
                        final_game DATE,
                        retro_id VARCHAR(255),
                        bbref_id VARCHAR(255)
);

