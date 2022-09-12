package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //own by class

    public static StudentCheckingDTO byObject(StudentChecking studentChecking){

        return new StudentCheckingDTO(studentChecking.getBalance(), studentChecking.getSecretKey(),studentChecking.getPrimaryOwner().getId(), studentChecking.getSecondaryOwner().getId(),studentChecking.getStatus());
    }
}
