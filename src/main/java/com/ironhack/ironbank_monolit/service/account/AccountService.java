package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.AccountDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAll();
}
