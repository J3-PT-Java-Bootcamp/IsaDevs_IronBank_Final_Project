package com.ironhack.iroronbank_monolit.model.account;

import com.ironhack.iroronbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    /*
    * BASICS Attributes of Account
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // --- ???

    private BigDecimal balance;

    private String secretKey;

    @OneToOne(mappedBy = "account")
    private User primaryOwner;

    @OneToOne(mappedBy = "secondaryOwner")
    private User secondaryOwner;

    private BigDecimal penaltyFee;

    private Date creationDate;

    private Date interestDate;

    private Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD




}
