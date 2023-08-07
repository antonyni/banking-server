package com.codedifferently.cdbankapi.domain.withdrawl.controller;

import com.codedifferently.cdbankapi.domain.withdrawl.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawl.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.withdrawl.service.WithdrawalService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/withdrawal")
@CrossOrigin("*")
public class WithdrawalController {

    private WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService){
        this.withdrawalService = withdrawalService;
    }

    @PostMapping("")
    public ResponseEntity<Withdrawal> createWithdrawal(@RequestBody @NonNull Withdrawal withdrawal){
        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(withdrawal);
        return new ResponseEntity<>(createdWithdrawal, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Withdrawal> updateWithdrawal(@RequestBody @NonNull Withdrawal withdrawal, @PathVariable @NonNull long id){
        try{
            Withdrawal updateWithdrawal  = withdrawalService.updateWithdrawal(withdrawal, id);
            System.out.println(updateWithdrawal);
            return new ResponseEntity<>(updateWithdrawal, HttpStatus.OK);
        }catch (WithdrawalException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawals(){
        List<Withdrawal> withdrawal = withdrawalService.getAllWithdrawals();
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Withdrawal> getWithdrawalById(@PathVariable @NonNull long id){
        try {
            Withdrawal withdrawal = withdrawalService.getWithdrawalById(id);
            return new ResponseEntity<>(withdrawal, HttpStatus.OK);
        }catch (WithdrawalException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteWithdrawalById(@PathVariable @NonNull long id){
        try {
            Boolean result = withdrawalService.deleteWithdrawalById(id);
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }catch (WithdrawalException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
