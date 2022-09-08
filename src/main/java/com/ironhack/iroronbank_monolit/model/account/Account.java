package com.ironhack.iroronbank_monolit.model.account;

import com.ironhack.iroronbank_monolit.model.enums.Status;
import com.ironhack.iroronbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
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

    protected BigDecimal balance;

    protected String secretKey;

    @OneToOne
    @JoinColumn(name = "owner")
    protected User primaryOwner;

    @OneToOne
    @JoinColumn(name = "secondary_owner")
    protected User secondaryOwner;

    @Enumerated(EnumType.STRING)
    protected Status status;

    protected BigDecimal penaltyFee;

    protected Date creationDate;

    protected Date interestDate;

    protected Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD

    @ManyToOne
    @JoinColumn(name = "accounts")
    protected User accounts;

    public Account(BigDecimal balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, BigDecimal penaltyFee, Date creationDate, Date interestDate, Date transactionDate, User accounts) {
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
