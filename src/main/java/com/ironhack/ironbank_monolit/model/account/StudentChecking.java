package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,Date interestDate, Date transactionDate, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, interestDate, transactionDate, accounts);
    }
}
