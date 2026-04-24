package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
  @Autowired
  private AccountService accountService;
  @Autowired
  private MessageService messageService;

  @PostMapping("/register")
  public ResponseEntity<Account> register(@RequestBody Account account){
    Account saved =accountService.register(account);
    if(saved==null){
      return ResponseEntity.status(400).body(null);
    }
    return ResponseEntity.ok(saved);
  }
  @PostMapping("/login")
  public ResponseEntity<Account> login(@RequestBody Account account){
    Account user=accountService.login(account);
    if(user==null){
      return ResponseEntity.status(401).body(null);
    }
    return ResponseEntity.ok(user);
  }
@PostMapping("/messages")
public ResponseEntity<Message> createMessage(@RequestBody Message message){

    Message saved = messageService.createMessage(message);

    if(saved == null){
        return ResponseEntity.status(400).body(null);
    }

    return ResponseEntity.ok(saved);
}
@GetMapping("/messages")
public ResponseEntity<List<Message>> getAllMessages(){
    return ResponseEntity.ok(messageService.getAllMessages());
}
@GetMapping("/messages/{id}")
public ResponseEntity<Message> getMessageById(@PathVariable int id){
    return ResponseEntity.ok(messageService.getMessageById(id));
}
@DeleteMapping("/messages/{id}")
public ResponseEntity<Integer> deleteMessage(@PathVariable int id){
    return ResponseEntity.ok(messageService.deletMessageById(id));
}
@PatchMapping("/messages/{id}")
public ResponseEntity<Integer> updateMessage(@PathVariable int id, @RequestBody Message message){

    Integer result = messageService.updateMessageById(id, message.getMessageText());

    if(result == null){
        return ResponseEntity.status(400).body(null);
    }

    return ResponseEntity.ok(result);
}
@GetMapping("/accounts/{accountId}/messages")
public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable int accountId){
    return ResponseEntity.ok(messageService.getMessagesByUser(accountId));
}
}
