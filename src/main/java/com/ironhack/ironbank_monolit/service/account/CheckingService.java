package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;

import java.util.List;

public interface CheckingService {

    List<Checking> getStatus(String stats);
}
