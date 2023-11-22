package com.group04.docker.controller;

import com.group04.docker.dto.ChatRequest;
import com.group04.docker.dto.mapper.ChatMapper;
import com.group04.docker.model.Chat;
import com.group04.docker.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Transactional(readOnly = true)
    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getAllChats() {
        return new ResponseEntity<>(chatService.getAllChats(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/chats/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        return new ResponseEntity<>(chatService.getChatById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/customers/{customerId}/suppliers/{supplierId}/chats")
    public ResponseEntity<Chat> createChat(@PathVariable String customerId, @PathVariable String supplierId, @RequestBody ChatRequest chatRequest) {
        var chat = ChatMapper.INSTANCE.chatRequestToChat(chatRequest);
        return new ResponseEntity<>(chatService.saveChat(customerId, supplierId, chat), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/chats/{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable String id, @RequestBody ChatRequest chatRequest) {
        var chat = ChatMapper.INSTANCE.chatRequestToChat(chatRequest);
        return new ResponseEntity<>(chatService.updateChat(id, chat), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/chats/{id}")
    public ResponseEntity<String> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return new ResponseEntity<>(String.format("Chat with id %s was successfully deleted", id), HttpStatus.NO_CONTENT);
    }

}
