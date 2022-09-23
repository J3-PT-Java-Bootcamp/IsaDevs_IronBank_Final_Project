package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.*;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;
import com.ironhack.ironbank_monolit.model.account.Money;


import java.math.BigDecimal;
import java.util.List;

public interface AdminService {

    //*************************** BY ADMIN

    List <AdminDTO> getAdmins();

    //*********************************** BY ACCOUNTS

    Object getAccountById(String typus, Long id) throws Exception;

    AccountHolderDTO saveNewAccount(AccountHolderDTO accountHolderDTO, String accountsType, Money creditLimit, BigDecimal interestRate, Money balance, String secretkey );

    AccountHolderDTO addNewAccount(Long id, String accountsType, Money creditLimit, BigDecimal interestRate, Money balance, String secretkey ) throws Exception;




}
