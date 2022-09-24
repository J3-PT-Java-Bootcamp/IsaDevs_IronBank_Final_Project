package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.*;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.ThirdPartyDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.validation.Operations;


import java.math.BigDecimal;
import java.util.List;

public interface AdminService {

    //************************************************************************************
    // BY ADMIN
    //************************************************************************************

    List <AdminDTO> getAdmins();

    AdminDTO getByUserName(String name);

    List <AdminDTO> getByName(String name);


    //************************************************************************************
    //  BY ACCOUNTS
    //************************************************************************************

    List <Object> getTotal(String typus);
    Object getAccountById(String typus, Long id) throws Exception;

    AccountHolderDTO saveNewAccount(AccountHolderDTO accountHolderDTO, String accountsType, Money creditLimit, BigDecimal interestRate, Money balance, String secretkey );

    AccountHolderDTO addNewAccount(Long id, String accountsType, Money creditLimit, BigDecimal interestRate, Money balance, String secretkey ) throws Exception;


    //************************************************************************************
    //  BY OPERATIONS (BALANCES AND OTHERS)
    //************************************************************************************

    Money getBalanceByAccount(Long id) throws Exception;

    List <Money> getBalanceByUser(Long id) throws Exception;

    Account modifyBalance(Long id, BigDecimal balance);


    //************************************************************************************
    //  DELETE REGISTERS
    //************************************************************************************

    void deleteAccount(Long id) throws Exception;

    void deleteUser(Long id) throws Exception;

    //************************************************************************************
    //  BY THIRD PARTY
    //************************************************************************************

    List<ThirdPartyDTO> total();


    //************************************************************************************
    //  BY OPERATIONS
    //************************************************************************************

    List<Operations> getTotal();
}
