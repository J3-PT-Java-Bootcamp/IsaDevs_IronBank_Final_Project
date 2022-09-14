package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Account;


import java.util.List;

public interface AdminService {

    //*********************************** BY CHECKING
    List <Account> getAll();
    List <CheckingDTO> getAllChecking();
    List<CheckingDTO> getStatus(String stats);

    CheckingDTO findById(Long id);



    //***************  JUST FOR TESTER RESPONSE

    CheckingDTO saveObject(CheckingDTO checkingDTO);


    //*********************************** BY CREDIT

    List<CreditDTO> getAllCredit();

    CreditDTO findByIdCredit(Long id);

    CreditDTO saveObject(CreditDTO creditDTO);


    //*********************************** BY SAVING

    List<SavingDTO> getAllSaving();

    SavingDTO findByIdSaving(Long id);

    SavingDTO saveObject(SavingDTO savingDTO);

    //*********************************** BY STUDENT

    List<StudentCheckingDTO> getAllStudent();

    StudentCheckingDTO findByIdStudent(Long id);

    StudentCheckingDTO saveObjectStudent(StudentCheckingDTO studentCheckingDTO);

}
