package com.ironhack.ironbank_monolit.controller;

import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import com.ironhack.ironbank_monolit.validation.Operations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/member")
public class AccountHolderController {

    private final AccountHolderServiceImpl accountHolderService;

    private final OperationServiceImpl operationService;


    public AccountHolderController(AccountHolderServiceImpl accountHolderService, OperationServiceImpl operationService) {
        this.accountHolderService = accountHolderService;
        this.operationService = operationService;
    }

    //@GetMapping("/account-balance/{id}")
    @GetMapping("/account-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List <Money> getMyBalances(@PathVariable("id") Long  id) throws Exception {

        return accountHolderService.getBalanceByUser(id);
    }

    @PatchMapping("/make-a-transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account makeAtransfer(@RequestBody NewRegisterDTO newRegisterDTO) throws Exception {

        return operationService.transfer(newRegisterDTO.getPrimary(), newRegisterDTO.getId(), newRegisterDTO.getName(), newRegisterDTO.getBalance(), newRegisterDTO.getRol(), newRegisterDTO.getSecretId());
    }

    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.OK)
    public List <Operations> gettotal(){
        return accountHolderService.getTotal();
    }

}
