package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;

import java.util.List;
import java.util.Map;

public interface AccountHolderService {
    List <AccountHolderDTO> holders();

    AccountHolderDTO byId(long id);

    List <AccountHolderDTO> getByName(String name);

    List <AccountHolderDTO> getByUserName(String username);

}
