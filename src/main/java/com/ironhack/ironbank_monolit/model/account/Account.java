package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    /*
    * BASICS Attributes of Account
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id; // --- ???

    //protected BigDecimal balance;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_balance"))
    })
    @Embedded
    protected Money balance; //need to be Money

    protected String secretKey;

    @OneToOne
    @JoinColumn(name = "owner")
    protected User primaryOwner;

    @OneToOne
    @JoinColumn(name = "secondary_owner")
    protected User secondaryOwner;

    @Enumerated(EnumType.STRING)
    protected Status status;

    /*@AttributeOverrides({
            @AttributeOverride(name = "penaltyFee", column = @Column(name = "penalty_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_penalty_fee"))
    })
    @Embedded*/
    @Transient
    protected Money penaltyFee = new Money(new BigDecimal("40")); //need to be Money

    protected Date creationDate;

    protected Date interestDate; // --> SERÁ A LIBRE ELECCION DEL ADMIN O DEL USER LA FECHA DE INTERÉS O SERÁ AUTOMÁTICA

    //this must be start just when the new operation is done
    protected Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD

    @ManyToOne
    @JoinColumn(name = "accounts")
    protected User accounts;


    //changes to money attribute
    public Account(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.status = status;
        setCreationDate();
        setInterestDate();
        this.accounts = accounts;
    }

    /*
    * this method capture the date for every new account
    * */
    public void setCreationDate() {
        this.creationDate = new Date();
    }

    public void setInterestDate() {
        this.interestDate = getCreationDate();
    }

    /*
    *
    The penaltyFee for all accounts (that have this attribute) should be 40.
    If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically

    * */
    /*public void penaltyFeeChecker(){
        if(getBalance().getAmount()){

        }
    }*/
}
