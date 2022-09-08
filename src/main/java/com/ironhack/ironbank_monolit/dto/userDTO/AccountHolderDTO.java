package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.user.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String mailingAddress;
}
