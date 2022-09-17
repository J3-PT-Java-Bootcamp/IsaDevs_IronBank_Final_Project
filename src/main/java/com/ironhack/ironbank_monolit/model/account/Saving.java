package com.ironhack.ironbank_monolit.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.model.enums.InterestType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Saving extends Account {

    @Transient
    //@DecimalMin(value = "100")
    private Money minimalBalance;

    @DecimalMax(value = "0.5")
    private BigDecimal interestRate = new BigDecimal("0.0025");

    @Transient
    private Money MINIMUM_BALANCE = new Money(new BigDecimal("100"));

    @Transient
    private Money DEFAULT_BALANCE =  new Money(new BigDecimal("1000"));

    private BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");

    private BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");

    // admin constructor
    public Saving(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
        setMinimalBalance(balance);
        setInterestRate(interestRate);
    }

    //user constructor, every values are by default
    public Saving(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
        super.setBalance(MINIMUM_BALANCE);
        this.interestRate = getInterestRate();
    }

    public static Saving byDTO(SavingDTO savingDTO, User primaryOwner, User secondaryOwner ){

        return new Saving(savingDTO.getBalance(), savingDTO.getSecretKey(), primaryOwner, secondaryOwner, savingDTO.getStatus(),null, savingDTO.getInterestRate());
    }


    @Override
    public void setBalance(Money balance){
        super.setBalance(getMinimalBalance());
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

        if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) > 0){
            this.interestRate = MAXIMUM_INTEREST_RATE;
        } else if (interestRate.compareTo(DEFAULT_INTEREST_RATE) < 0) {
            this.interestRate = DEFAULT_INTEREST_RATE;
        } else {
            this.interestRate = interestRate;
        }
    }


    @JsonIgnore
    public Money getBalance() {
        //call to the method for check the interest Rate IF A SAVING COUNT--> ANNUALLY
        super.addInterestRate(interestRate, InterestType.ANNUALLY);
        penaltyFeeChecker(MINIMUM_BALANCE);

        return balance;
    }
}
