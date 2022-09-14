package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.repository.account.*;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.repository.user.AdminRepository;
import com.ironhack.ironbank_monolit.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
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
