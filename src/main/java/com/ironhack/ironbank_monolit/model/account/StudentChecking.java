package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, List<Operations> operationSend, List<Operations> operationReceive) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, operationSend, operationReceive);
    }

    public static StudentChecking byDTO(StudentCheckingDTO studentCheckingDTO, User primaryOwner, User Secondary) {

        return new StudentChecking(studentCheckingDTO.getBalance(), studentCheckingDTO.getSecretKey(), primaryOwner, Secondary, studentCheckingDTO.getStatus(),studentCheckingDTO.getSend(), studentCheckingDTO.getReceive());
    }
}
