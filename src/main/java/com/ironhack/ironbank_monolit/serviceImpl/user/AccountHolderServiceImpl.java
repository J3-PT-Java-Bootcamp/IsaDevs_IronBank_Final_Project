package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(value = "account-holder")
public class AccountHolderServiceImpl implements AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public List<AccountHolderDTO> holders() {
        var hol = accountHolderRepository.findAll();
        List <AccountHolderDTO> list = new ArrayList<>();

        for(var i : hol){
            list.add(AccountHolderDTO.byObject(i));
        }
        return list;
    }

    @Override
    public AccountHolderDTO byId(Long id) {
        var byInstance = accountHolderRepository.findById(id).orElseThrow();
        var byDTO = AccountHolderDTO.byObject(byInstance);

        return byDTO;
    }

    @Override
    public List<AccountHolderDTO> getByName(String name) {
       var names = accountHolderRepository.findAccountHolderByName(name);
       List <AccountHolderDTO> namesOf = new ArrayList<>();
       for(var i : names){
           namesOf.add(AccountHolderDTO.byObject(i));
       }

       return namesOf;
    }

    @Override
    public List<AccountHolderDTO> getByUserName(String username) {
        var names = accountHolderRepository.findAccountHolderByUserName(username);
        List <AccountHolderDTO> namesOf = new ArrayList<>();
        for(var i : names){
            namesOf.add(AccountHolderDTO.byObject(i));
        }

        return namesOf;
    }


    public AccountHolderDTO save(AccountHolderDTO accountHolderDTO){

        var byInstance = AccountHolder.byDTO(accountHolderDTO);

        accountHolderRepository.save(byInstance);
        var byDto = AccountHolderDTO.byObject(byInstance);

        return byDto;
    }

    public void deleteUser(Long id){
        accountHolderRepository.deleteById(id);
    }
}
