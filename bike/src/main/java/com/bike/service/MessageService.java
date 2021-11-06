package com.bike.service;

import com.bike.model.Message;
import com.bike.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return messageRepository.getMessage(messageId);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> e = messageRepository.getMessage(message.getIdMessage());
            if (e.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public boolean deleteMessage(int id) {

        Optional<Message> message = getMessage(id);

        if (message.isEmpty()) {
            return false;
        } else {
            messageRepository.delete(message.get());
            return true;
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> mensaje = messageRepository.getMessage(message.getIdMessage());
            if (!mensaje.isEmpty()) {
                if (message.getMessageText() != null) {
                    mensaje.get().setMessageText(message.getMessageText());
                }
                
                return messageRepository.save(mensaje.get());
            }
        }
        return message;
    }

}
