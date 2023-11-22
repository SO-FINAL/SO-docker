package com.group04.docker.service.Impl;

import com.group04.docker.model.Chat;
import com.group04.docker.repository.ChatRepository;
import com.group04.docker.repository.CustomersRepository;
import com.group04.docker.repository.SuppliersRepository;
import com.group04.docker.service.ChatService;
import com.group04.docker.utils.exception.ResourceNotFoundException;
import com.group04.docker.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    CustomersRepository customerRepository;

    @Autowired
    SuppliersRepository supplierRepository;

    @Override
    public Chat getChatById(String id) {
        existChatById(id);
        return chatRepository.findById(id).get();
    }

    @Override
    public Chat saveChat(String customerId, String supplierId, Chat chat) {
        existsCustomerById(customerId);
        existsSupplierById(supplierId);
        validateChat(chat);
        chat.setSentAt(LocalDateTime.now());
        chat.setCustomer(customerRepository.findById(customerId).get());
        chat.setSupplier(supplierRepository.findById(supplierId).get());
        return chatRepository.save(chat);
    }

    @Override
    public Chat updateChat(String id, Chat chat) {
        existChatById(id);
        validateChat(chat);
        var chatToUpdate = chatRepository.findById(id).get();
        chatToUpdate.setSentBy(chat.getSentBy());
        chatToUpdate.setMessage(chat.getMessage());
        return chatRepository.save(chatToUpdate);
    }

    @Override
    public void deleteChat(String id) {
        existChatById(id);
        chatRepository.deleteById(id);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    private void existChatById(String id) {
        if (!chatRepository.existsBy_id(id)) {
            throw new ResourceNotFoundException("Chat with id " + id + " not found");
        }
    }

    private void existsSupplierById(String supplierId) {
        if (!supplierRepository.existsBy_id(supplierId)) {
            throw new ResourceNotFoundException("Supplier with id " + supplierId + " not found");
        }
    }

    private void existsCustomerById(String customerId) {
        if (!customerRepository.existsBy_id(customerId)) {
            throw new ResourceNotFoundException("Customer with id " + customerId + " not found");
        }
    }

    private void validateChat(Chat chat) {
        if (chat.getSentBy() == null || chat.getSentBy().isEmpty()) {
            throw new ValidationException("Sent by cannot be null or empty");
        }
        if (chat.getMessage() == null || chat.getMessage().isEmpty()) {
            throw new ValidationException("Message cannot be null or empty");
        }
    }

}
