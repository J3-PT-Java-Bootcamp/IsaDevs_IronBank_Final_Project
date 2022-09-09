package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentCheckingDTO {

    private Money balance;

    private String secretKey;

    private User primaryOwner;

    private User secondaryOwner;

    private Status status;

    private User accounts;

    //own by class

    public static StudentCheckingDTO byObject(StudentChecking studentChecking){
        var dtoStudent = new StudentCheckingDTO();
        dtoStudent.setBalance(studentChecking.getBalance());
        dtoStudent.setSecretKey(studentChecking.getSecretKey());
        dtoStudent.setPrimaryOwner(studentChecking.getPrimaryOwner());
        dtoStudent.setSecondaryOwner(studentChecking.getSecondaryOwner());
        dtoStudent.setStatus(studentChecking.getStatus());
        dtoStudent.setAccounts(studentChecking.getAccounts());

        return dtoStudent;
    }
}
