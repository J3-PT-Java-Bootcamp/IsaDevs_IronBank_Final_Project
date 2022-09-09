package com.ironhack.ironbank_monolit.model.account;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @AttributeOverrides({
            @AttributeOverride(name = "penaltyFee", column = @Column(name = "penalty_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_penalty_fee"))
    })
    @Embedded
    protected Money penaltyFee; //need to be Money

    protected Date creationDate;

    protected Date interestDate;

    protected Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD

    @ManyToOne
    @JoinColumn(name = "accounts")
    protected User accounts;


    //changes to money attribute
    public Account(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, Money penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.status = status;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.interestDate = interestDate;
        this.transactionDate = transactionDate;
        this.accounts = accounts;
    }


}
