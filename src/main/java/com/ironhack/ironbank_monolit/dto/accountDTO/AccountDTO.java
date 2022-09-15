package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDTO {

    private Money balance;

    private String secretKey;

   // private long primaryOwner;
    private User primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    //own by class

    public static AccountDTO byObject(Account account){

        return new AccountDTO(account.getBalance(), account.getSecretKey(),account.getPrimaryOwner(), account.getSecondaryOwner().getId(),account.getStatus());
    }
}
