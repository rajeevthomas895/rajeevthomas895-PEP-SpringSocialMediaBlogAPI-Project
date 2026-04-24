package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;

  public Account register(Account account){
    if(account.getUsername()==null || account.getUsername().isBlank()){
      return null;
    }
    if(account.getPassword()==null || account.getPassword().length()<4){
      return null;
    }
    if(accountRepository.findByUsername(account.getUsername())!=null){
      return null;
    }
    return accountRepository.save(account);
  }
  public Account login(Account account){
    Account existing=accountRepository.findByUsername(account.getUsername());
    if(existing==null){
      return null;
    }
    if(!existing.getPassword().equals(account.getPassword())){
      return null;
    }
    return existing;
  }
}
