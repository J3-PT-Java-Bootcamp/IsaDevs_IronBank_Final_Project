package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CreditDTO {

    private Money balance;

    private String secretKey;

    private User primaryOwner;

    private User secondaryOwner;

    private Status status;

    private User accounts;

    //own by class
    private Money creditLimit;

    private BigDecimal interestRate;


    public static CreditDTO  byObject(Credit credit){
        var dtoCredit = new CreditDTO();
        dtoCredit.setBalance(credit.getBalance());
        dtoCredit.setSecretKey(credit.getSecretKey());
        dtoCredit.setPrimaryOwner(credit.getPrimaryOwner());
        dtoCredit.setSecondaryOwner(credit.getSecondaryOwner());
        dtoCredit.setStatus(credit.getStatus());
        dtoCredit.setAccounts(credit.getAccounts());
        dtoCredit.setCreditLimit(credit.getCreditLimit());
        dtoCredit.setInterestRate(credit.getInterestRate());

        return dtoCredit;
    }
}
