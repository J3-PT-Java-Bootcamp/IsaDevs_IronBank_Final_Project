package com.ironhack.ironbank_monolit.model.account;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checking extends Account{

    @Transient
    private Money MINIMAL_BALANCE = new Money(new BigDecimal("250"));
    @Transient
    private Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12"));


    //this gonna be charges by DTO
    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,User accounts, Money MINIMAL_BALANCE, Money MONTHLY_MAINTENANCE_FEE) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
        this.MINIMAL_BALANCE = MINIMAL_BALANCE;
        this.MONTHLY_MAINTENANCE_FEE = MONTHLY_MAINTENANCE_FEE;
    }

    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
    }
}
