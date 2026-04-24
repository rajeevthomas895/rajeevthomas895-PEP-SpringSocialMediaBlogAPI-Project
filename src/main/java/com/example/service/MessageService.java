package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;
  private AccountRepository accountRepository;

  public Message createMessage(Message message){
    if(message.getMessageText()==null || message.getMessageText().isBlank() || message.getMessageText().length()>255){
      return null;
    }
    if(message.getPostedBy()==null||!accountRepository.existsById(message.getPostedBy())){
      return null;
    }
    return messageRepository.save(message);
  }
  public List<Message> getAllMessages(){
    return messageRepository.findAll();
  }
  public Message getMessageById(int id){
    return messageRepository.findById(id).orElse(null);
  }
  public Integer deletMessageById(int id){
    if(messageRepository.existsById(id)){
      messageRepository.deleteById(id);
      return 1;
    }
    return null;
  }
  public Integer updateMessageById(int id,String text){
    if(text==null || text.isBlank() || text.length()>255){
      return null;
    }
    Optional<Message> optional=messageRepository.findById(id);
    if(optional.isPresent()){
      Message msg=optional.get();
      msg.setMessageText(text);
      messageRepository.save(msg);
      return 1;
    }
    return null;
  }
  public List<Message> getMessagesByUser(int accountId){
    return messageRepository.findByPostedBy(accountId);
  }
}
