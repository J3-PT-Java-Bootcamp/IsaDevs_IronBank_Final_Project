package com.ironhack.ironbank_monolit.validation;


import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.repository.account.AccountRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service(value = "operations")
public class OperationServiceImpl {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;


    /*the user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.*/
    public Account transfer(long iduser, long id, String name, BigDecimal ammount) throws Exception {
        var userReceive = accountHolderRepository.findByIdAndName(id, name);
        var user = accountHolderRepository.findById(iduser).orElseThrow();

        var account = accountRepository.findByPrimaryOwner(accountHolderRepository.findById(iduser).orElseThrow());

        var acountReceive = accountRepository.findByPrimaryOwner(accountHolderRepository.findByIdAndName(id, name));

        /*
        *  check if exist a user with id and name
        * */
        if(userReceive == null){
            throw new Exception("Not Found");
        } else {
            if(user.getOwner().getBalance().getAmount().compareTo(ammount) == 1 ){
                //user.getOwner().setBalance(new Money(user.getOwner().getBalance().getAmount().subtract(ammount)));
                //user.getOwner().setTransactionDate(new Date());

               //accountHolderRepository.save(user);

                account.setBalance(new Money(user.getOwner().getBalance().getAmount().subtract(ammount)));
                account.setTransactionDate(new Date());

                acountReceive.setBalance(new Money(userReceive.getOwner().getBalance().getAmount().add(ammount)));
                acountReceive.setTransactionDate(new Date());

                accountRepository.save(account);
                accountRepository.save(acountReceive);
                ///////////////////

                //userReceive.getOwner().setBalance(new Money(userReceive.getOwner().getBalance().getAmount().add(ammount)));
                //userReceive.getOwner().setTransactionDate(new Date());

                //accountHolderRepository.save(userReceive);

                //account.setBalance(new Money(account.getBalance().getAmount().subtract(ammount)));



                System.out.println("transfer ok");
            }
        }

        return account;
    }


    public Money checkBalance(long id) throws Exception {

        var user = accountHolderRepository.findById(id).orElseThrow();

        var account = accountRepository.findByPrimaryOwner(user);

        if(account == null){
            throw new Exception("not user register with that id");
        }

        return account.getBalance();
    }
}
