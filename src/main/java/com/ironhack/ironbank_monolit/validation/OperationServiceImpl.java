package com.ironhack.ironbank_monolit.validation;


import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(value = "operations")
public class OperationServiceImpl {

    @Autowired
    private AccountHolderRepository accountHolderRepository;


    /*the user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.*/
    public Account transfer(long id, String name, BigDecimal ammount) throws Exception {
        var user = accountHolderRepository.findByIdAndName(id, name);

        if(user == null){
            throw new Exception("Not Found");
        } else {
            if(user.getOwner().getBalance().getAmount().compareTo(ammount) == 1 ){
                user.getOwner().setBalance(user.getOwner().getBalance().decreaseAmount(ammount));
            }
        }

        return null;
    }
}
