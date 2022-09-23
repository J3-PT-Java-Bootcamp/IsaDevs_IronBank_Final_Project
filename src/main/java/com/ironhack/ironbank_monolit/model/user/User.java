package com.ironhack.ironbank_monolit.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner")
    protected List <Account> owner;

    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner")
    protected List <Account> secondaryOwner;

    protected String secret;

    protected String userName;

    public User(String name, List<Account> owner, List<Account> secondaryOwner, String secret, String userName) {
        this.name = name;
        this.owner = owner;
        setOwner(new ArrayList<>());
        setSecondaryOwner(new ArrayList<>());
        this.secret = secret;
        this.userName = userName;

    }

    public void setOwner(List<Account> owner) {
        this.owner = owner;
    }

    public void setSecondaryOwner(List<Account> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public void addOwnerToAccount(Account account){
        if(owner.contains(account)){
            return;
        }

        owner.add(account);
    }

    public void addSecondaryToAccount(Account account){
        if(secondaryOwner.contains(account)){
            return;
        }

        secondaryOwner.add(account);
    }
}
