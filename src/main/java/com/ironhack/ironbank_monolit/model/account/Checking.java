package com.ironhack.ironbank_monolit.model.account;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checking extends Account{

    private BigDecimal MINIMAL_BALANCE = new BigDecimal("250");
    private BigDecimal MONTHLY_MAINTENANCE_FEE = new BigDecimal("12");


    //this gonna be charges by DTO
    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,Money penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts, BigDecimal MINIMAL_BALANCE, BigDecimal MONTHLY_MAINTENANCE_FEE) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
        this.MINIMAL_BALANCE = MINIMAL_BALANCE;
        this.MONTHLY_MAINTENANCE_FEE = MONTHLY_MAINTENANCE_FEE;
    }

    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, Money penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
    }
}
