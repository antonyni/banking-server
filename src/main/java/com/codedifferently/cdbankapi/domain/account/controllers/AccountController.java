package com.codedifferently.cdbankapi.domain.account.controllers;

import com.codedifferently.cdbankapi.domain.account.exceptions.AccountException;
import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/AccountRequests")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<Account> createAccountRequest(@RequestBody Account account)  {
        try {
            Account savedAccount = accountService.create(account);
            ResponseEntity response = new ResponseEntity(savedAccount, HttpStatus.CREATED);
            return response;
        } catch (AccountException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accountEntities = accountService.getAllAccounts();
        ResponseEntity<List<Account>> response = new ResponseEntity<>(accountEntities, HttpStatus.OK);
        return response;
    }

    @GetMapping("/login")
    public ResponseEntity<Integer> loginChecker(@RequestBody String[] info) {
        try {
            Integer result = accountService.loginChecker(info[0], info[1]);
            ResponseEntity<Integer> response = new ResponseEntity<>(result, HttpStatus.OK);
            return response;
        } catch (AccountException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        try {
            Account account = accountService.getAccountById(id);
            ResponseEntity<?> response = new ResponseEntity<>(account, HttpStatus.OK);
            return response;
        } catch (AccountException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Account account){
        try{
            Account updatedAccount = accountService.updateAccount(id, account);
            ResponseEntity response = new ResponseEntity(updatedAccount,HttpStatus.OK);
            return response;
        } catch (AccountException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    // Endpoint for deleting a Widget by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try{
            accountService.deleteUser(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (AccountException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}





