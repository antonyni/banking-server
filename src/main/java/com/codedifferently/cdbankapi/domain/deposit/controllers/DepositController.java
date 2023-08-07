package com.codedifferently.cdbankapi.domain.deposit.controllers;

import com.codedifferently.cdbankapi.domain.deposit.exceptions.DepositException;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.deposit.services.DepositService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposit")
@CrossOrigin("*")
public class DepositController {

    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


    @PostMapping("")
    public ResponseEntity<Deposit> createDeposit(@RequestBody @NonNull Deposit deposit) {
        try {
            Deposit createdDeposit = depositService.createDeposit(deposit);
            return new ResponseEntity<>(createdDeposit, HttpStatus.CREATED);
        } catch (DepositException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Deposit> updateDeposit(@RequestBody @NonNull Deposit deposit, @PathVariable @NonNull long id) {
        try {
            Deposit updatedDeposit = depositService.updateDeposit(deposit, id);
            return new ResponseEntity<>(updatedDeposit, HttpStatus.OK);
        } catch (DepositException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Deposit>> getAllDeposits() {
        List<Deposit> deposits = depositService.getAllDeposits();
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable @NonNull long id) {
        try {
            Deposit deposit = depositService.getDepositById(id);
            return new ResponseEntity<>(deposit, HttpStatus.OK);
        } catch (DepositException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteDepositById(@PathVariable @NonNull long id) {
        try {
            Boolean result = depositService.deleteDepositById(id);
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        } catch (DepositException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
