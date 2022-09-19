package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
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
public class StudentCheckingDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    private List<Operations> send;

    private List <Operations> receive;

    //own by class

    public static StudentCheckingDTO byObject(StudentChecking studentChecking){

        var send = studentChecking.getOperationSend();
        List<Operations> sending = new ArrayList<>();

        var receive = studentChecking.getOperationReceive();
        List <Operations> receiver = new ArrayList<>();

        for(var i : send){
            sending.add(i);
        }
        studentChecking.setOperationSend(sending);

        for(var i : receive){
            receiver.add(i);
        }
        studentChecking.setOperationReceive(receiver);

        return new StudentCheckingDTO(studentChecking.getBalance(), studentChecking.getSecretKey(),studentChecking.getPrimaryOwner().getId(), studentChecking.getSecondaryOwner().getId(),studentChecking.getStatus(), studentChecking.getOperationSend(), studentChecking.getOperationReceive());
    }
}
