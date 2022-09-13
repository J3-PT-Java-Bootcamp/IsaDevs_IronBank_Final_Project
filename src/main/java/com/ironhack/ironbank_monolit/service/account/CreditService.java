package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;

import java.util.List;

public interface CreditService {

    List<CreditDTO> getAll();

    CreditDTO findById(Long id);

    CreditDTO saveObject(CreditDTO creditDTO);
}
