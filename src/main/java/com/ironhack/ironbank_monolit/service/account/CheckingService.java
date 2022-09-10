package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;

import java.util.List;

public interface CheckingService {

    List <CheckingDTO> getAll();
    List<CheckingDTO> getStatus(String stats);
}
