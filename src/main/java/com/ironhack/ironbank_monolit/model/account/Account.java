package com.ironhack.ironbank_monolit.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.model.enums.InterestType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    /*
    * BASICS Attributes of Account
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id; // --- ???

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_balance"))
    })
    @Embedded
    protected Money balance; //need to be Money

    @NotBlank
    protected String secretKey;

    @OneToOne
    @JoinColumn(name = "owner")
    protected User primaryOwner;

    @OneToOne
    @JoinColumn(name = "secondary_owner")
    protected User secondaryOwner;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @Transient
    protected Money penaltyFee = new Money(new BigDecimal("40")); //need to be Money

    protected Date creationDate;

    protected Date interestDate; // --> SERÁ A LIBRE ELECCION DEL ADMIN O DEL USER LA FECHA DE INTERÉS O SERÁ AUTOMÁTICA

    //this must be start just when the new operation is done
    protected Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "accounts")
    protected User accounts;


    // 1 ACCOUNT == N OPERATIONS
    //@OneToMany(mappedBy = "account")
    //private List <Operation> operation;


    //changes to money attribute
    public Account(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status, User accounts) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.status = Status.ACTIVE;
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
    public void penaltyFeeChecker(Money minimum){
        if(getBalance().getAmount().compareTo(minimum.getAmount()) < 0 ){
            this.balance = new Money(getBalance().decreaseAmount(penaltyFee));
        }
    }

    public void addInterestRate(BigDecimal interestRate, InterestType typus){

        switch (typus){
            case MONTHLY -> {
                Date month = new Date(getInterestDate().toString());
                month.setMonth(month.getMonth() + 1);

                //checking if the interest month compare to actual month is the same for add the interest
                if(month.compareTo(new Date()) == 0){
                    setBalance(new Money(getBalance().getAmount().multiply(interestRate)));
                }
            }
            case ANNUALLY -> {
                Date year = new Date(getInterestDate().toString());
                year.setYear(year.getYear() + 1);

                //checking if the interest year compare to actual year is the same for add the interest
                if(year.compareTo(new Date()) == 0){
                    setBalance(new Money(getBalance().getAmount().multiply(interestRate)));
                }

            }



        }
    }
}
