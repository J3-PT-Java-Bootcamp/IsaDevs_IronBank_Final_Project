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
    private long id;

    private String name;

    @OneToOne(mappedBy = "primaryOwner")
    private Account owner;

    @OneToOne(mappedBy = "secondaryOwner")
    private Account secondaryOwner;


    @OneToMany(mappedBy = "accounts")
    private List <Account> accountList;

    
}
