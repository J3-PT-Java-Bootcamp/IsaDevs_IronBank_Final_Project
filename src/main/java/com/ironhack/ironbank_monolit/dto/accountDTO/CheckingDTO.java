package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckingDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    //private Money  minimalBalance;

    //private List<Operations> send;

    //private List <Operations> receive;

    private Money  monthlyMaintenanceFee;




    public static CheckingDTO byObject(Checking checking){

        var send = checking.getOperationSend();
        List<Operations> sending = new ArrayList<>();

        var receive = checking.getOperationReceive();
        List <Operations> receiver = new ArrayList<>();

        for(var i : send){
            sending.add(i);
        }
        checking.setOperationSend(sending);

        for(var i : receive){
            receiver.add(i);
        }
        checking.setOperationReceive(receiver);

        return new CheckingDTO(checking.getBalance(),checking.getSecretKey(), checking.getPrimaryOwner().getId(), checking.getSecondaryOwner().getId(), checking.getStatus(), /*checking.getOperationSend(), checking.getOperationReceive(),*/ checking.getMONTHLY_MAINTENANCE_FEE());
    }


}
