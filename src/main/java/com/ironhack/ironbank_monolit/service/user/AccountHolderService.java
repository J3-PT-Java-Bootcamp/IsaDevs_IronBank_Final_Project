package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.validation.Operations;

import java.util.List;
public interface AccountHolderService {
    List <AccountHolderDTO> holders();

    AccountHolderDTO byId(Long id);

    List <AccountHolderDTO> getByName(String name);

    List<AccountHolderDTO> getByUserName(String username);

    List<Operations> getTotal();

    AccountHolderDTO save(AccountHolderDTO accountHolderDTO);

    List <Money> getBalanceByUser(Long id) throws Exception;



}
