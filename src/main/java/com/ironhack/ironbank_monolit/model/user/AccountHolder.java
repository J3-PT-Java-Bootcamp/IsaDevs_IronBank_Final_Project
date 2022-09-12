package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.*;
import com.ironhack.ironbank_monolit.model.enums.AccountsType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.ironhack.ironbank_monolit.model.enums.AccountsType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User {

    //@DateTimeFormat(pattern = "MM-dd-yyyy")
    private static Date dateOfBirth = new Date(1985);

    @Embedded
    private Address address;

    private String mailingAddress;


    public AccountHolder(String name, Account owner, Account secondaryOwner, List<Account> accountList, Date dateOfBirth, Address address, String mailingAddress) {
        super(name, owner, secondaryOwner, accountList);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    public static Date getDateOfBirth() {
        return dateOfBirth;
    }

    /*
    * THIS METHOD VERIFIED IF A STUDENTCHECKING OR NOT
    *
    * When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
    * */
    public static Account primaryOwnerVerified(AccountsType account, Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts, Money MINIMAL_BALANCE, Money MONTHLY_MAINTENANCE_FEE,  Money creditLimit, BigDecimal interestRate){
        /*if(getDateOfBirth().getYear() - new Date().getYear() >= 24){
            return new Checking(balance, secretKey, primaryOwner, secondaryOwner, status,  interestDate, transactionDate, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE);
        }
        else {
            return  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  interestDate, transactionDate, accounts);
        }
        */
        switch (account){
            case CHECKING -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Checking(balance, secretKey, primaryOwner, secondaryOwner, status, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
            }
            case CREDIT -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Credit(balance, secretKey, primaryOwner, secondaryOwner, status, accounts, creditLimit, interestRate) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
            }
            case SAVING -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Saving(balance, secretKey, primaryOwner, secondaryOwner, status, accounts, MINIMAL_BALANCE, interestRate) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
            }
            case STUDENT -> {
                return  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
            }

        }
        return  null;

        //return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Checking(balance, secretKey, primaryOwner, secondaryOwner, status, accounts, MINIMAL_BALANCE, MONTHLY_MAINTENANCE_FEE) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status,  accounts);
    }


    //public static AccountHolder byDTO(AccountHolderDTO accountHolderDTO, Account primaryOwner, Account secondaryOwner ){
    public static AccountHolder byDTO(AccountHolderDTO accountHolderDTO){
        var addressa = new Address(accountHolderDTO.getNumber(), accountHolderDTO.getRoad(), accountHolderDTO.getCountry(), accountHolderDTO.getPostalCode());
        //return new AccountHolder(accountHolderDTO.getName(), primaryOwner, secondaryOwner, null, accountHolderDTO.getDateOfBirth(), addressa, accountHolderDTO.getMailingAddress());
        return new AccountHolder(accountHolderDTO.getName(), null, null,null, accountHolderDTO.getDateOfBirth(), addressa, accountHolderDTO.getMailingAddress());
    }
}
