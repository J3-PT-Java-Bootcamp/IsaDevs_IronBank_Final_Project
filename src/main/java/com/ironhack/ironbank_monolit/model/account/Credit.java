package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credit extends Account {

    private BigDecimal creditLimit;
    private BigDecimal interestRate;

    private Money MIN_CREDIT_LIMIT = new Money(new BigDecimal("100"));

    private Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000));

    private BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.1");

    //private BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.2");

    /*
    * if (a < b) {}         // For primitive double
        if (A.compareTo(B) < 0)  {} // For BigDecimal

        Actually compareTo returns -1(less than), 0(Equal), 1(greater than) according to values.
    * */

    public Credit(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, Date interestDate, Date transactionDate, User accounts, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status,interestDate, transactionDate, accounts);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setCreditLimit(Money creditLimit){
        if(MIN_CREDIT_LIMIT.getAmount().compareTo(creditLimit.getAmount()) < 0){
            this.creditLimit = MIN_CREDIT_LIMIT.getAmount();
        } else if (creditLimit.getAmount().compareTo(MAX_CREDIT_LIMIT.getAmount()) < 0) {
            this.creditLimit = creditLimit.getAmount();
        } else {
            this.creditLimit = creditLimit.getAmount();
        }
    }


    public void setInterestRate(BigDecimal interestRate){
        if(interestRate.compareTo(MIN_INTEREST_RATE) < 0){
            this.interestRate = MIN_INTEREST_RATE;
        }
        else {
            this.interestRate = interestRate;
        }
    }
}
