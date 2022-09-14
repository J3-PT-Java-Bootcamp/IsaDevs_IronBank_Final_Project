package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.model.enums.InterestType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credit extends Account {

    @Transient
    //@DecimalMin(value = "100")
    //@DecimalMax(value = "100000")
    private Money creditLimit;

    //@DecimalMin(value = "0.1")
    //@DecimalMax(value = "0.2")
    private BigDecimal interestRate;

    @Transient
    //@DecimalMin(value = "100.0")
    private Money MIN_CREDIT_LIMIT = new Money(new BigDecimal("100"));

    @Transient
    private Money MAX_CREDIT_LIMIT = new Money(new BigDecimal("100000"));

    private BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.1");

    private BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.2");

    /*
    * if (a < b) {}         // For primitive double
        if (A.compareTo(B) < 0)  {} // For BigDecimal

        Actually compareTo returns -1(less than), 0(Equal), 1(greater than) according to values.
    * */

    // only for admin
    public Credit(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status,accounts);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    // only for new users not admin
    public Credit(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
        this.creditLimit = MIN_CREDIT_LIMIT; //BY DEFAULT
        this.interestRate = MAX_INTEREST_RATE;
    }

    public static Credit byDTO(CreditDTO creditDTO, User primaryOwner, User Secondary){

        return new Credit(creditDTO.getBalance(), creditDTO.getSecretKey(), primaryOwner, Secondary, creditDTO.getStatus(),null, creditDTO.getCreditLimit(), creditDTO.getInterestRate());
    }

    /*
    * THIS METHOD CHECK AND SET THE VALUES WITH THE REQUIREMENTS
    * */
    public void setCreditLimit(Money creditLimit){
        if(creditLimit.getAmount().compareTo(MIN_CREDIT_LIMIT.getAmount()) == -1){
            this.creditLimit = MIN_CREDIT_LIMIT;
        } else if (creditLimit.getAmount().compareTo(MAX_CREDIT_LIMIT.getAmount()) == 1) {
            this.creditLimit = MAX_CREDIT_LIMIT;
        } else {
            this.creditLimit = creditLimit;
        }
    }

    /*
     * THIS METHOD CHECK AND SET THE VALUES WITH THE REQUIREMENTS
     * */
    public void setInterestRate(BigDecimal interestRate){
        if(interestRate.compareTo(MIN_INTEREST_RATE) == -1){
            this.interestRate = MIN_INTEREST_RATE;
        }
        else if (interestRate.compareTo(MAX_INTEREST_RATE) == 1){
            this.interestRate = MAX_INTEREST_RATE;
        }
        else {
            this.interestRate = interestRate;
        }
    }

    public Money getBalance() {
        //call to the method for check the interest Rate IF A CREDIT COUNT--> MONTHLY
        super.addInterestRate(interestRate, InterestType.MONTHLY);

        return balance;
    }

}
