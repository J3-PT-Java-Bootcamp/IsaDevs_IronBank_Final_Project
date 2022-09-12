package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDTO {

    private String name;

    //private long owner;

    //private long secondaryOwner;

    //by class

    private Date dateOfBirth;


    private Integer number;
    private String road;
    private String country;
    private Long postalCode;

    private String mailingAddress;

    public static AccountHolderDTO byObject(AccountHolder accountHolder){

        /*var list = accountHolder.getAccountList();
        List <Account> arrayAdd = new ArrayList<>();

        if(!list.isEmpty()){
            for(var i : list){
                arrayAdd.add(i);
            }
        }

        accountHolder.setAccountList(arrayAdd);*/

        //return new AccountHolderDTO(accountHolder.getName(), accountHolder.getOwner().getId() ,accountHolder.getSecondaryOwner().getId() , accountHolder.getDateOfBirth(), accountHolder.getAddress().getNumber(), accountHolder.getAddress().getRoad(), accountHolder.getAddress().getCountry(),  accountHolder.getAddress().getPostalCode(), accountHolder.getMailingAddress());

        return new AccountHolderDTO(accountHolder.getName(),  AccountHolder.getDateOfBirth(), accountHolder.getAddress().getNumber(), accountHolder.getAddress().getRoad(), accountHolder.getAddress().getCountry(),  accountHolder.getAddress().getPostalCode(), accountHolder.getMailingAddress());
    }


}
