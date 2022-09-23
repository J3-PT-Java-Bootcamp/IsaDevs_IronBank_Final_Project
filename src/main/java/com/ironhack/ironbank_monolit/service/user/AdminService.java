package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.*;
import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;


import java.util.List;

public interface AdminService {

    //*************************** BY ADMIN

    List <AdminDTO> getAdmins();

    //*********************************** BY CHECKING


    List<CheckingDTO> getStatus(String stats);




    //***************  JUST FOR TESTER RESPONSE

    CheckingDTO saveObject(CheckingDTO checkingDTO);


    //*********************************** BY CREDIT



    CreditDTO saveObject(CreditDTO creditDTO);


    //*********************************** BY SAVING

    SavingDTO saveObject(SavingDTO savingDTO);

    //*********************************** BY STUDENT

    StudentCheckingDTO saveObjectStudent(StudentCheckingDTO studentCheckingDTO);

}
