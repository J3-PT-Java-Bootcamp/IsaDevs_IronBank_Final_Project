package com.ironhack.ironbank_monolit.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User {

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Embedded
    private Address address;

    private String mailingAddress;


    public AccountHolder(String name, Account owner, Account secondaryOwner, /*List<Account> accountList,*/ Date dateOfBirth, Address address, String mailingAddress) {
        super(name, owner, secondaryOwner/*, accountList*/);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    /*
    * THIS METHOD VERIFIED IF A STUDENTCHECKING OR NOT
    *
    * When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
    * */
    public Account primaryOwnerVerified(AccountsType account, Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,  Money creditLimit, BigDecimal interestRate){
        System.out.println("age of " + getDateOfBirth().getYear());

        switch (account){
            case CHECKING -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Checking(balance, secretKey, primaryOwner, secondaryOwner, status) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status);
            }
            case CREDIT -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Credit(balance, secretKey, primaryOwner, secondaryOwner, status,  creditLimit, interestRate) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status);
            }
            case SAVING -> {
                return getDateOfBirth().getYear() - new Date().getYear() >= 24 ? new Saving(balance, secretKey, primaryOwner, secondaryOwner, status,   interestRate) :  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status);
            }
            case STUDENT -> {
                return  new StudentChecking(balance, secretKey, primaryOwner, secondaryOwner, status);
            }
        }
        return  null;

    }


    //public static AccountHolder byDTO(AccountHolderDTO accountHolderDTO, Account primaryOwner, Account secondaryOwner ){
    public static AccountHolder byDTO(AccountHolderDTO accountHolderDTO){
        var addressa = new Address(accountHolderDTO.getNumber(), accountHolderDTO.getRoad(), accountHolderDTO.getCountry(), accountHolderDTO.getPostalCode());
        //return new AccountHolder(accountHolderDTO.getName(), primaryOwner, secondaryOwner, null, accountHolderDTO.getDateOfBirth(), addressa, accountHolderDTO.getMailingAddress());
        return new AccountHolder(accountHolderDTO.getName(), null, null,/*null,*/ accountHolderDTO.getDateOfBirth(), addressa, accountHolderDTO.getMailingAddress());
    }
}
