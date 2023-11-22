package com.group04.docker.dto.mapper;

import com.group04.docker.dto.ChatRequest;
import com.group04.docker.model.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    Chat chatRequestToChat(ChatRequest chatRequest);
}
