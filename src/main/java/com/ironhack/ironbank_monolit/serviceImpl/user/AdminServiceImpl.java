package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.*;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;
import com.ironhack.ironbank_monolit.model.account.*;
import com.ironhack.ironbank_monolit.model.enums.AccountsType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.*;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.repository.user.AdminRepository;
import com.ironhack.ironbank_monolit.repository.user.ThirdPartyRepository;
import com.ironhack.ironbank_monolit.service.user.AdminService;
import com.ironhack.ironbank_monolit.serviceImpl.account.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ironhack.ironbank_monolit.model.enums.AccountsType.CHECKING;

@Service(value = "admin")
public class AdminServiceImpl implements AdminService {

    private final  AccountHolderRepository accountHolderRepository;// for admin or accountholder??

    private final  AdminRepository adminRepository;


    //*/***************************** by services

    private final AccountHolderServiceImpl accountHolderService;

    private final CheckingServiceImpl checkingService;

    private final StudentCheckingServiceImpl studentCheckingService;

    private final CreditServiceImpl creditService;

    private final SavingServiceImpl savingService;

    private final AccountServiceImpl accountService;

    public AdminServiceImpl(AccountHolderRepository accountHolderRepository, AdminRepository adminRepository, AccountHolderServiceImpl accountHolderService, CheckingServiceImpl checkingService, StudentCheckingServiceImpl studentCheckingService, CreditServiceImpl creditService, SavingServiceImpl savingService, AccountServiceImpl accountService) {
        this.accountHolderRepository = accountHolderRepository;
        this.adminRepository = adminRepository;
        this.accountHolderService = accountHolderService;
        this.checkingService = checkingService;
        this.studentCheckingService = studentCheckingService;
        this.creditService = creditService;
        this.savingService = savingService;
        this.accountService = accountService;
    }

    @Override
    public List<AdminDTO> getAdmins() {
        var admin = adminRepository.findAll();
        List <AdminDTO> admins = new ArrayList<>();

        for(var i : admin){
            admins.add(AdminDTO.byObject(i));
        }
        return null;
    }

    //****************************************************************************************
    public List <Object> getTotal(String typus){

        AccountsType type = AccountsType.valueOf(typus.toUpperCase());

        switch (type){
            case ACCOUNTS -> {
                return Collections.singletonList(accountService.getAll());
            }
            case CHECKING -> {
                return Collections.singletonList(checkingService.getAll());
            }
            case CREDIT -> {
                return Collections.singletonList(creditService.getAll());
                //return Collections.singletonList((AccountDTO) creditService.getAll());
            }
            case SAVING -> {
                return Collections.singletonList(savingService.getAll());
            }
            case STUDENT -> {
                return Collections.singletonList(studentCheckingService.getAll());
            }
        }

        return null;

    }

    //************************************************************************************

//      THIS METHOD FIND A ACCOUNT WITH A SPECIFIC ID

    //*************************************************************


    public Object getAccountById(String typus, Long id){

        AccountsType type = AccountsType.valueOf(typus.toUpperCase());

        switch (type){
            case CHECKING -> {
                return checkingService.findById(id);
            }
            case CREDIT -> {
                return creditService.findById(id);
            }
            case SAVING -> {
                return savingService.findById(id);
            }
            case STUDENT -> {
                return studentCheckingService.findById(id);
            }
        }

        return null;

    }


    // *************************************************************

//      THIS METHOD ADD A NEW ACCOUNT WITH A ACCOUNT HOLDER ASSOCIATED
    //*************************************************************

    public AccountHolderDTO saveNewAccount(AccountHolderDTO accountHolderDTO, String accountsType, Money creditLimit, BigDecimal interestRate, Money balance, String secretkey ){

        accountHolderService.save(accountHolderDTO);

        var primaryOwner = accountHolderRepository.findById(accountHolderRepository.count());

        System.out.println(primaryOwner);

        AccountsType type = AccountsType.valueOf(accountsType.toUpperCase());


        var account = primaryOwner.get().primaryOwnerVerified(type, balance, secretkey, primaryOwner.orElseThrow(), primaryOwner.orElseThrow(), Status.ACTIVE,  creditLimit, interestRate);

        switch (type){
            case CHECKING -> {
                var dtoChecking = CheckingDTO.byObject((Checking) account);
                checkingService.saveObject(dtoChecking);
            }
            case CREDIT -> {
                var dtoChecking = CreditDTO.byObject((Credit) account);
                creditService.saveObject(dtoChecking);
            }
            case SAVING -> {
                var dtoChecking = SavingDTO.byObject((Saving) account);
                savingService.saveObject(dtoChecking);
            }
            case STUDENT -> {
                var dtoChecking = StudentCheckingDTO.byObject((StudentChecking) account);
                studentCheckingService.saveObject(dtoChecking);
            }
        }
        return accountHolderDTO;
    }

    //************************************************************
    @Override
    public List<CheckingDTO> getStatus(String stats) {
        return checkingService.getStatus(stats);
    }



    @Override
    public CheckingDTO saveObject(CheckingDTO checkingDTO) {
        return checkingService.saveObject(checkingDTO);
    }



    @Override
    public CreditDTO saveObject(CreditDTO creditDTO) {
        return creditService.saveObject(creditDTO);
    }

    @Override
    public SavingDTO saveObject(SavingDTO savingDTO) {
        return savingService.saveObject(savingDTO);
    }



    @Override
    public StudentCheckingDTO saveObjectStudent(StudentCheckingDTO studentCheckingDTO) {
        return null;
    }
}
