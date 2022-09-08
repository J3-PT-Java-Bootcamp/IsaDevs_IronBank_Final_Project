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

    private BigDecimal MIN_CREDIT_LIMIT = new BigDecimal("100");

    private BigDecimal MAX_CREDIT_LIMIT = new BigDecimal(100000);

    private BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.1");

    //private BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.2");

    /*
    * if (a < b) {}         // For primitive double
        if (A.compareTo(B) < 0)  {} // For BigDecimal

        Actually compareTo returns -1(less than), 0(Equal), 1(greater than) according to values.
    * */

    public Credit(BigDecimal balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, penaltyFee, creationDate, interestDate, transactionDate, accounts);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setCreditLimit(BigDecimal creditLimit){
        if(creditLimit.compareTo(MIN_CREDIT_LIMIT) < 0){
            this.creditLimit = MIN_CREDIT_LIMIT;
        } else if (creditLimit.compareTo(MAX_CREDIT_LIMIT) < 0) {
            this.creditLimit = creditLimit;
        } else {
            this.creditLimit = creditLimit;
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
