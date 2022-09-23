package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.user.AccountHolderService;
import com.ironhack.ironbank_monolit.serviceImpl.account.AccountServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "account-holder")
public class AccountHolderServiceImpl implements AccountHolderService {

    private final AccountHolderRepository accountHolderRepository;

    private final AccountServiceImpl accountService;

    public AccountHolderServiceImpl(AccountHolderRepository accountHolderRepository, AccountServiceImpl accountService) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountService = accountService;
    }

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

    public List <Money> getBalanceByUser(Long id) throws Exception {

        List <Money> balance = new ArrayList<>();

        if (accountService.getPrimaryOwner(id) == null){
            throw new Exception("Nonexistent account with that id");
        }
        for (var i : accountService.getPrimaryOwner(id)){
            balance.add(i.getBalance());
        }
        return balance;
    }
}
