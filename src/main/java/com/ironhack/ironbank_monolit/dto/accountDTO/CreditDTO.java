package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
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
public class CreditDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private List<Operations> send;

    //private List <Operations> receive;

    //private long accounts;

    //own by class
    private Money creditLimit;

    private BigDecimal interestRate;




    public static CreditDTO  byObject(Credit credit){

        var send = credit.getOperationSend();
        List<Operations> sending = new ArrayList<>();

        var receive = credit.getOperationReceive();
        List <Operations> receiver = new ArrayList<>();

        for(var i : send){
            sending.add(i);
        }
        credit.setOperationSend(sending);

        for(var i : receive){
            receiver.add(i);
        }
        credit.setOperationReceive(receiver);

        return new CreditDTO(credit.getBalance(), credit.getSecretKey(), credit.getPrimaryOwner().getId(), credit.getSecondaryOwner().getId(), credit.getStatus(),/*credit.getOperationSend(), credit.getOperationReceive(),*/ credit.getCreditLimit(), credit.getInterestRate());
    }
}
