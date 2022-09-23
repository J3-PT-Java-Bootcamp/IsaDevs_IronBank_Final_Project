package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.AccountDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.repository.account.AccountRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Override
    public List<AccountDTO> getAll() {
        var all = accountRepository.findAll();

        List <AccountDTO> listDTO = new ArrayList<>();
        for(var i : all){
            listDTO.add(AccountDTO.byObject(i));
        }

        return listDTO;
    }


    public AccountDTO getById(Long id){
        var acccoun = accountRepository.findById(id).orElseThrow();

        return AccountDTO.byObject(acccoun);
    }

    public List <AccountDTO> getPrimaryOwner(Long id){
        var owner = accountHolderRepository.findById(id).orElseThrow();
        var account = accountRepository.findByPrimaryOwner(owner);

        List <AccountDTO> owners = new ArrayList<>();

        for(var i : account){
            owners.add(AccountDTO.byObject(i));
        }

        return owners;
    }


    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
