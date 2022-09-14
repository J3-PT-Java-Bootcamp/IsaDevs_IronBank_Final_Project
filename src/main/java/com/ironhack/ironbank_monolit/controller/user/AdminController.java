package com.ironhack.ironbank_monolit.controller.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


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


    //********** OPERATIONS AREA

    @PatchMapping("/id-user/{idUser}/id/{id}/name/{name}/ammount/{ammount}")
    public Account makeAtransfer(@PathVariable("idUser") long idUser, @PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("ammount")BigDecimal ammount) throws Exception {
        return operationService.transfer(idUser, id, name, ammount);
    }
}
