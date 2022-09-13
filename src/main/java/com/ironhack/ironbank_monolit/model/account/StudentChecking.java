package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
    }

    public static StudentChecking byDTO(StudentCheckingDTO studentCheckingDTO, User primaryOwner, User Secondary) {

        return new StudentChecking(studentCheckingDTO.getBalance(), studentCheckingDTO.getSecretKey(), primaryOwner, Secondary, studentCheckingDTO.getStatus(),null);
    }
}
