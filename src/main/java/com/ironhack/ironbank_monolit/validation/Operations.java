package com.ironhack.ironbank_monolit.validation;

import com.ironhack.ironbank_monolit.model.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Operations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account accountSend;

    @ManyToOne
    private Account accountReceive;

    protected Date transactionDate;

    public Operations(Account accountSend, Account accountReceive, Date transactionDate) {
        this.accountSend = accountSend;
        this.accountReceive = accountReceive;
        this.transactionDate = transactionDate;
    }
}
