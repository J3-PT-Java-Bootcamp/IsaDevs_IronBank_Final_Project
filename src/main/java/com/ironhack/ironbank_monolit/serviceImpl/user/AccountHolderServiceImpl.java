package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "account-holder")
public class AccountHolderServiceImpl implements AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Override
    public Map<Object, Object> findByPassAnU(String pass, String name) {
        Map <Object, Object> count = new HashMap<>();
        List<Object[]> x = accountHolderRepository.findByPasswordAndUser(pass, name);
        for(int i = 0; i < x.size(); i++){
            count.put(x.get(i)[0], x.get(i)[1]);
        }

        System.out.println(count);

        return count;
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

    public List <AccountHolder> total(){
        return accountHolderRepository.findAll();
    }

    public AccountHolderDTO save(AccountHolderDTO accountHolderDTO){

        var byInstance = AccountHolder.byDTO(accountHolderDTO);

        accountHolderRepository.save(byInstance);
        var byDto = AccountHolderDTO.byObject(byInstance);

        return byDto;
    }
}
