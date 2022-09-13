package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;

import java.util.List;

public interface SavingService {

    List<SavingDTO> getAll();

    SavingDTO findById(Long id);

    SavingDTO saveObject(SavingDTO savingDTO);
}
