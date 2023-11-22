package com.group04.docker.service;

import com.group04.docker.model.Chat;

import java.util.List;

public interface ChatService {
    Chat getChatById(String id);
    Chat saveChat(String customerId, String supplierId, Chat chat);
    Chat updateChat(String id, Chat chat);
    void deleteChat(String id);
    List<Chat> getAllChats();
}
