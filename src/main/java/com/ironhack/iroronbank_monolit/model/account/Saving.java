package com.ironhack.iroronbank_monolit.model.account;

import com.ironhack.iroronbank_monolit.model.enums.Status;
import com.ironhack.iroronbank_monolit.model.user.User;
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

    private BigDecimal minimalBalance;
    private BigDecimal interestRate;

    private BigDecimal MINIMUM_BALANCE = new BigDecimal("100");

    private BigDecimal DEFAULT_BALANCE =  new BigDecimal("1000");

    private BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");

    private BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");

    public Saving(BigDecimal balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts, BigDecimal minimalBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
        setMinimalBalance(minimalBalance);
        setInterestRate(interestRate);
    }

    public void setMinimalBalance(BigDecimal minimalBalance){

        if(minimalBalance.compareTo(MINIMUM_BALANCE) < 0){
            this.minimalBalance = MINIMUM_BALANCE;
        }
        else{
            this.minimalBalance = DEFAULT_BALANCE;
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
