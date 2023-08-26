package com.challenge.enocabank.controller;

import com.challenge.enocabank.dto.AccountDTO;
import com.challenge.enocabank.dto.ResponseDTO;
import com.challenge.enocabank.dto.WithdrawOrAddMoneyRequest;
import com.challenge.enocabank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private static final String ENTITY_NAME = "account";
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity<ResponseDTO<AccountDTO>> createAccount(@RequestBody AccountDTO accountDTO){
        AccountDTO savedEntity = accountService.createAccount(accountDTO);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(savedEntity);
        responseDTO.setMessage("Account creation successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<ResponseDTO<AccountDTO>> updateAccount(@PathVariable(value = "id") Long id,@RequestBody AccountDTO accountDTO){
        AccountDTO updatedEntity = accountService.update(accountDTO);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(updatedEntity);
        responseDTO.setMessage("Account update successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<ResponseDTO<List<AccountDTO>>> getAccountsByCustomerId(@PathVariable Long id){
        List<AccountDTO> accountDTOS = accountService.findAllByCustomerId(id);
        ResponseDTO<List<AccountDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(accountDTOS);
        responseDTO.setMessage("Successful getting account with customer id",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<ResponseDTO<AccountDTO>> getOneAccountById(@PathVariable Long id){
        AccountDTO accountDTO = accountService.findOneAccountById(id);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(accountDTO);
        responseDTO.setMessage("Get account successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<ResponseDTO> deleteAccount(@PathVariable Long id){
        accountService.deleteAccountById(id);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Delete account successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/account/withdraw")
    public ResponseEntity<ResponseDTO<AccountDTO>> withdrawMoney(@RequestBody WithdrawOrAddMoneyRequest request){
        AccountDTO accountDTO = accountService.withdrawMoney(request);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(accountDTO);
        responseDTO.setMessage("Withdrawal successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/account/add-money")
    public ResponseEntity<ResponseDTO<AccountDTO>> addMoney(@RequestBody WithdrawOrAddMoneyRequest request){
        AccountDTO accountDTO = accountService.addMoney(request);
        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(accountDTO);
        responseDTO.setMessage("Add money successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }
}
