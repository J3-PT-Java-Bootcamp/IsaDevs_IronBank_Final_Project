package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.*;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.*;
import com.ironhack.ironbank_monolit.model.enums.AccountsType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.account.*;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.repository.user.AdminRepository;
import com.ironhack.ironbank_monolit.service.user.AdminService;
import com.ironhack.ironbank_monolit.serviceImpl.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service(value = "admin")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;// for admin or accountholder??

    @Autowired
    private AdminRepository adminRepository;


    //*/***************************** by services

    @Autowired
    private AccountHolderServiceImpl accountHolderService;

    @Autowired
    private CheckingServiceImpl checkingService;

    @Autowired
    private StudentCheckingServiceImpl studentCheckingService;

    @Autowired
    private CreditServiceImpl creditService;

    @Autowired
    private SavingServiceImpl savingService;

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    public List<AccountDTO> getAll() {

        return accountService.getAll();
    }

    @Override
    public List<CheckingDTO> getAllChecking() {
        var all = checkingRepository.findAll();

        List <CheckingDTO> listDTO = new ArrayList<>();
        for(var i : all){
            listDTO.add(CheckingDTO.byObject(i));
        }
        return listDTO;
    }



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
        return null;
    }

    @Override
    public CheckingDTO findById(Long id) {
        return null;
    }

    @Override
    public CheckingDTO saveObject(CheckingDTO checkingDTO) {
        return null;
    }

    @Override
    public List<CreditDTO> getAllCredit() {
        return null;
    }

    @Override
    public CreditDTO findByIdCredit(Long id) {
        return null;
    }

    @Override
    public CreditDTO saveObject(CreditDTO creditDTO) {
        return null;
    }

    @Override
    public List<SavingDTO> getAllSaving() {
        return null;
    }

    @Override
    public SavingDTO findByIdSaving(Long id) {
        return null;
    }

    @Override
    public SavingDTO saveObject(SavingDTO savingDTO) {
        return null;
    }

    @Override
    public List<StudentCheckingDTO> getAllStudent() {
        return null;
    }

    @Override
    public StudentCheckingDTO findByIdStudent(Long id) {
        return null;
    }

    @Override
    public StudentCheckingDTO saveObjectStudent(StudentCheckingDTO studentCheckingDTO) {
        return null;
    }
}
