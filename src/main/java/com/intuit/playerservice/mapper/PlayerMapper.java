package com.intuit.playerservice.mapper;

import com.intuit.playerservice.domain.Player;
import com.intuit.playerservice.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlayerMapper {

    PlayerDto toDto(Player player);

}