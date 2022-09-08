package com.ironhack.ironbank_monolit.dto.accountDTO;

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
public class SavingDTO {

    private BigDecimal balance;

    private String secretKey;

    private User primaryOwner;

    private User secondaryOwner;

    private Status status;

    private BigDecimal penaltyFee;

    private Date creationDate;

    private Date interestDate;

    private Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD

    private User accounts;

    //own by class
    private BigDecimal minimalBalance;

    private BigDecimal interestRate;
}
