package com.ironhack.ironbank_monolit.controller.user;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
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

    @PatchMapping("/id-user/{idUser}/id-account/{id}/name/{name}/ammount/{ammount}")
    public Account makeAtransfer(@PathVariable("idUser") long idUser, @PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("ammount") BigDecimal ammount) throws Exception {
        return operationService.transfer(idUser, id, name, ammount);
    }

}
