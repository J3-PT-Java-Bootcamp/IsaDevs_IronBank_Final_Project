package com.ironhack.iroronbank_monolit.model.user;

import com.ironhack.iroronbank_monolit.model.account.Account;
import com.ironhack.iroronbank_monolit.model.account.Checking;
import com.ironhack.iroronbank_monolit.model.account.StudentChecking;
import com.ironhack.iroronbank_monolit.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User {

    //@DateTimeFormat
    private Date dateOfBirth;

    @Embedded
    private Address address;

    private String mailingAddress;

    /*
    * THIS METHOD VERIFIED IF A STUDENTCHECKING OR NOT
    *
    * When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
    * */
    public Account primaryOwnerVerified(BigDecimal balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts, BigDecimal MINIMAL_BALANCE, BigDecimal MONTHLY_MAINTENANCE_FEE){
        if(getDateOfBirth().getYear() - new Date().getYear() >= 24){
            return new Checking(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE);
        }
        else {
            return  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
        }
    }
}
