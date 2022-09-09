package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
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
    public Account primaryOwnerVerified(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,  User accounts, Money MINIMAL_BALANCE, Money MONTHLY_MAINTENANCE_FEE){
        /*if(getDateOfBirth().getYear() - new Date().getYear() >= 24){
            return new Checking(balance, secretKey, primaryOwner, secondaryOwner, status,  interestDate, transactionDate, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE);
        }
        else {
            return  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  interestDate, transactionDate, accounts);
        }
        */

        return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Checking(balance, secretKey, primaryOwner, secondaryOwner, status, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
    }
}
