package com.ironhack.ironbank_monolit.validation;


import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.AccountRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service(value = "operations")
public class OperationServiceImpl {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;


    /*the user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.*/
    public Account transfer(long iduser, long id, String name, BigDecimal amount) throws Exception {

        var userName = accountHolderRepository.findByName(name);

        var user = accountHolderRepository.findById(iduser).orElseThrow();

        var account = accountRepository.findByPrimaryOwner(accountHolderRepository.findById(iduser).orElseThrow());

        var acountReceive = accountRepository.findById(id).orElseThrow();

        var userReceive = accountHolderRepository.findByName(acountReceive.getPrimaryOwner().getName());

        if (userName == null && !Objects.equals(userName.getName(), userReceive.getName())) {
            throw new Exception("Not User with that name");
        } else {
            if (user.getOwner().getBalance().getAmount().compareTo(amount) > 0 && user.getOwner().getStatus() != Status.FROZEN) {
                user.getOwner().setBalance(new Money(user.getOwner().getBalance().decreaseAmount(amount)));
                user.getOwner().setTransactionDate(new Date());
                account.setBalance(new Money(user.getOwner().getBalance().getAmount().subtract(amount)));
                //account.getOperation();

                userReceive.getOwner().setBalance(new Money(userReceive.getOwner().getBalance().increaseAmount(amount)));
                userReceive.getOwner().setTransactionDate(new Date());
                acountReceive.setBalance(new Money(userReceive.getOwner().getBalance().getAmount().add(amount)));

                accountRepository.save(account);
                accountRepository.save(acountReceive);

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
