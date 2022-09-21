package com.ironhack.ironbank_monolit.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @NotNull
    protected String name;

    @OneToOne(mappedBy = "primaryOwner")
    protected Account owner;

    @OneToOne(mappedBy = "secondaryOwner")
    protected Account secondaryOwner;

    protected String secret;

    protected String userName;

    /*@OneToMany(mappedBy = "accounts")
    @JsonIgnore
    protected List <Account> accountList;*/

    public User(String name, Account owner, Account secondaryOwner, String secretKey, String userName) {
        this.name = name;
        this.owner = owner;
        this.secondaryOwner = secondaryOwner;
        this.secret = secretKey;
        this.userName = userName;
    }

    public User(String name, Account owner, Account secondaryOwner) {
        this.name = name;
        this.owner = owner;
        this.secondaryOwner = secondaryOwner;
    }
}
