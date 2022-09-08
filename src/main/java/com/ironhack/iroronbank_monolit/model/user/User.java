package com.ironhack.iroronbank_monolit.model.user;

import com.ironhack.iroronbank_monolit.model.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String name;

    @OneToOne(mappedBy = "primaryOwner")
    protected Account owner;

    @OneToOne(mappedBy = "secondaryOwner")
    protected Account secondaryOwner;

    @OneToMany(mappedBy = "accounts")
    protected List <Account> accountList;

    public User(String name, Account owner, Account secondaryOwner, List<Account> accountList) {
        this.name = name;
        this.owner = owner;
        this.secondaryOwner = secondaryOwner;
        this.accountList = accountList;
    }
}
