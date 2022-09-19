package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.Saving;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavingDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    //own by class
    //private Money minimalBalance;

    private List<Operations> send;

    private List <Operations> receive;

    private BigDecimal interestRate;


    public static SavingDTO byObject(Saving saving){

        var send = saving.getOperationSend();
        List<Operations> sending = new ArrayList<>();

        var receive = saving.getOperationReceive();
        List <Operations> receiver = new ArrayList<>();

        for(var i : send){
            sending.add(i);
        }
        saving.setOperationSend(sending);

        for(var i : receive){
            receiver.add(i);
        }
        saving.setOperationReceive(receiver);

        return new SavingDTO(saving.getBalance(),saving.getSecretKey(), saving.getPrimaryOwner().getId(), saving.getSecondaryOwner().getId(), saving.getStatus(), saving.getOperationSend(), saving.getOperationReceive(), saving.getInterestRate());
    }

}
