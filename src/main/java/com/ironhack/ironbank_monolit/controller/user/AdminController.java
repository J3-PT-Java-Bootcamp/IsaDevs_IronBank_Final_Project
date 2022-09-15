package com.ironhack.ironbank_monolit.controller.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.AccountsType;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("account-holder")
    private AccountHolderServiceImpl accountHolderService;

    @Autowired
    @Qualifier("admin")
    private AdminServiceImpl adminService;

    @Autowired
    @Qualifier("operations")
    private OperationServiceImpl operationService;

    @GetMapping("/accounts")
    public List<Account> findAll(){
        return adminService.getAll();
    }

    @GetMapping("/checking")
    public List <CheckingDTO> findAllChecking(){
        return adminService.getAllChecking();
    }
    @GetMapping("/credits")
    public List <CreditDTO> findAllCredit(){
        return adminService.getAllCredit();
    }

    @GetMapping("/users")
    public List <AccountHolderDTO> all(){
        return accountHolderService.holders();
    }

    //*********  DONT WORK WITH REPOSITORY
    @GetMapping("/users-total")
    public List <AccountHolder> total(){
        return accountHolderService.total();
    }

    //**********************************

    @GetMapping("/id-user/{id}")
    public AccountHolderDTO findById(@PathVariable("id") long id){
        return accountHolderService.byId(id);
    }
    //********** ADD NEW REGISTER TO DATABASE

    @PostMapping("/add-new-register")
    public AccountHolderDTO createNewAccount(@RequestBody String name, @RequestBody Date date, @RequestBody Integer number, @RequestBody String road, @RequestBody String country, @RequestBody Long postalCode, @RequestBody String mailingAddress, @RequestBody String accountType, @RequestBody BigDecimal interestRate, @RequestBody BigDecimal creditLimit, @RequestBody BigDecimal balance, @RequestBody String secretKey){
        AccountHolderDTO newUser = new AccountHolderDTO(name, date, number, road, country, postalCode, mailingAddress);

        var interest = new Money(interestRate);
        var balan = new Money(balance);

        return adminService.saveNewAccount(newUser, accountType,interest, creditLimit, balan, secretKey);
    }



    //********** OPERATIONS AREA

    @PatchMapping("/id-user/{idUser}/id/{id}/name/{name}/ammount/{ammount}")
    public Account makeAtransfer(@PathVariable("idUser") long idUser, @PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("ammount")BigDecimal ammount) throws Exception {
        return operationService.transfer(idUser, id, name, ammount);
    }


}
