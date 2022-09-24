package com.ironhack.ironbank_monolit.validation;


import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.account.AccountRepository;
import com.ironhack.ironbank_monolit.repository.operations.OperationsRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.security.rol.Rol;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service(value = "operations")
public class OperationServiceImpl {

    private final AccountHolderRepository accountHolderRepository;

    private final AccountRepository accountRepository;

    private final OperationsRepository operationsRepository;

    public OperationServiceImpl(AccountHolderRepository accountHolderRepository, AccountRepository accountRepository, OperationsRepository operationsRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountRepository = accountRepository;
        this.operationsRepository = operationsRepository;
    }


    /*the user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.*/
    public Account transfer(long iduser,  long id, String name, BigDecimal amount, String rol, String secretKey) throws Exception {

        var userName = accountHolderRepository.findAccountHolderByName(name).get(0);

        var user = accountHolderRepository.findById(iduser).orElseThrow();

        var account = accountRepository.findByPrimaryOwner(accountHolderRepository.findById(iduser).orElseThrow());

        var acountReceive = accountRepository.findById(id).orElseThrow();

        var userReceive = accountHolderRepository.findAccountHolderByName(acountReceive.getPrimaryOwner().getName()).get(0);

        String roles = rol.toUpperCase();

        switch (Rol.valueOf(roles)){
            case THIRD_PARTY -> thirdPartyTransfer(acountReceive, userReceive, amount, secretKey);
            case ACCOUNT_HOLDER -> {
                if (userName == null || !Objects.equals(userName.getName(), userReceive.getName()) || userName.getOwner().get(0).getStatus() == Status.FROZEN) {
                    throw new Exception("Not transfer, revised the conditions");
                } else {
                    if (user.getOwner().get(0).getBalance().getAmount().compareTo(amount) > 0) {

                        account.get(0).setBalance(new Money(user.getOwner().get(0).getBalance().getAmount().subtract(amount)));
                        acountReceive.setBalance(new Money(userReceive.getOwner().get(0).getBalance().getAmount().add(amount)));

                        var oper = new Operations(account.get(0), acountReceive, new Date());

                        account.get(0).addToOperationSendList(oper);
                        acountReceive.addToOperationReceiveList(oper);

                        accountRepository.save(account.get(0));
                        accountRepository.save(acountReceive);
                        operationsRepository.save(oper);

                        System.out.println("transfer ok");
                    } else {
                        System.out.println("Transfer abort");
                        throw new Exception("Not Founds for this transaction, find a job!!!");
                    }
                }
            }
        }


        return account.get(0);
    }

    public void thirdPartyTransfer(Account accountReceive, AccountHolder userReceive, BigDecimal amount, String secretKeycloakID) throws Exception {

        var aux = accountHolderRepository.findBySecret(secretKeycloakID);
        var receive = accountReceive;

        if (userReceive == null || userReceive.getOwner().get(0).getStatus() == Status.FROZEN  || aux == null) {
            throw new Exception("Not User with that name");
        }

        receive.setBalance(new Money(userReceive.getOwner().get(0).getBalance().getAmount().add(amount)));
        var oper = new Operations(null, receive, new Date());

        receive.addToOperationSendList(oper);
        accountRepository.save(receive);
        operationsRepository.save(oper);

        System.out.println("Transfer from thirdparty --->   ok");
    }


    /*public Money checkBalance(long id) throws Exception {

        var user = accountHolderRepository.findById(id).orElseThrow();

        var account = accountRepository.findByPrimaryOwner(user);

        if(account == null){
            throw new Exception("not user register with that id");
        }

        return account.getBalance();
    }*/
}
