package com.ironhack.ironbank_monolit.service.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;

import java.util.List;

public interface StudentCheckingService {
    List<StudentCheckingDTO> getAll();

    StudentCheckingDTO findById(Long id);

    StudentCheckingDTO saveObject(StudentCheckingDTO studentCheckingDTO);
}
