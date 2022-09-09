package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Saving extends Account {

    private Money minimalBalance;
    private BigDecimal interestRate;

    private Money MINIMUM_BALANCE = new Money(new BigDecimal("100"));

    private Money DEFAULT_BALANCE =  new Money(new BigDecimal("1000"));

    private BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");

    private BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");

    public Saving(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, Date interestDate, Date transactionDate, User accounts, Money minimalBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status,interestDate, transactionDate, accounts);
        setMinimalBalance(minimalBalance);
        setInterestRate(interestRate);
    }

    public void setMinimalBalance(Money minimalBalance){

        if(minimalBalance.getAmount().compareTo(MINIMUM_BALANCE.getAmount()) < 0){
            this.minimalBalance = MINIMUM_BALANCE;
        }
        else if (minimalBalance.getAmount().compareTo(DEFAULT_BALANCE.getAmount()) == 0){
                this.minimalBalance = DEFAULT_BALANCE;
        } else {
            this.minimalBalance = minimalBalance;
        }
    }

    public void setInterestRate(BigDecimal interestRate){

        if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) < 0){
            this.interestRate = interestRate;
        } else if (interestRate.compareTo(DEFAULT_INTEREST_RATE) < 0) {
            this.interestRate = DEFAULT_INTEREST_RATE;
        } else {
            this.interestRate = interestRate;
        }
    }
}
