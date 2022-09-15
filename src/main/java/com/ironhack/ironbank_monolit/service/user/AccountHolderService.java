package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;

import java.util.List;
import java.util.Map;

public interface AccountHolderService {

    Map<Object, Object> findByPassAnU(String pass, String  user);

    List <AccountHolderDTO> holders();
}
