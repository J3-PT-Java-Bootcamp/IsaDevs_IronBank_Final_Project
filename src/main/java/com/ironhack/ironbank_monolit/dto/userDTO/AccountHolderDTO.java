package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.model.user.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AccountHolderDTO {

    private String name;

    private Account owner;

    private Account secondaryOwner;

    private List<Account> accountList;

    //by class

    private Date dateOfBirth;

    private Address address;

    private Integer number;
    private String road;
    private String country;
    private Long postalCode;

    private String mailingAddress;


    public static AccountHolderDTO byObject(AccountHolder accountHolder){
        var dtoAccountHolder = new AccountHolderDTO();
        dtoAccountHolder.setName(accountHolder.getName());
        dtoAccountHolder.setOwner(accountHolder.getOwner());
        dtoAccountHolder.setSecondaryOwner(accountHolder.getSecondaryOwner());
        dtoAccountHolder.setDateOfBirth(accountHolder.getDateOfBirth());
        dtoAccountHolder.setMailingAddress(accountHolder.getMailingAddress());

        if(accountHolder.getAddress() != null){
            dtoAccountHolder.setNumber(accountHolder.getAddress().getNumber());
            dtoAccountHolder.setRoad(accountHolder.getAddress().getRoad());
            dtoAccountHolder.setCountry(accountHolder.getAddress().getCountry());
            dtoAccountHolder.setPostalCode(accountHolder.getAddress().getPostalCode());
        }

        var list = accountHolder.getAccountList();
        List <Account> arrayAdd = new ArrayList<>();

        if(!list.isEmpty()){
            for(var i : list){
                arrayAdd.add(i);
            }
        }

        dtoAccountHolder.setAccountList(arrayAdd);

        return dtoAccountHolder;
    }
}
