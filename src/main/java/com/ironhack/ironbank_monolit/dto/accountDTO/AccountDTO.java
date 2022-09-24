package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDTO {

    private Money balance;

    private String secretKey;

    private User primaryOwner;

    private Optional <Long> secondaryOwner;

    private Status status;

    private List<Operations> send;

    private List <Operations> receive;

    //own by class

    public static AccountDTO byObject(Account account){

        var send = account.getOperationSend();
        List <Operations> sending = new ArrayList<>();

        var receive = account.getOperationReceive();
        List <Operations> receiver = new ArrayList<>();

        for(var i : send){
            sending.add(i);
        }
        account.setOperationSend(sending);

        for(var i : receive){
            receiver.add(i);
        }
        account.setOperationReceive(receiver);

        return new AccountDTO(account.getBalance(), account.getSecretKey(),account.getPrimaryOwner(), Optional.of(account.getSecondaryOwner().getId()),account.getStatus(), account.getOperationSend(), account.getOperationReceive());
    }
}
