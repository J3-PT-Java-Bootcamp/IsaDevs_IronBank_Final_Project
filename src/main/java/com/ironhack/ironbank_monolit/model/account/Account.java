package com.ironhack.ironbank_monolit.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.ironbank_monolit.dto.accountDTO.AccountDTO;
import com.ironhack.ironbank_monolit.model.enums.InterestType;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;

import com.ironhack.ironbank_monolit.validation.Operations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JsonIgnore
    @JoinColumn(name = "owner")
    protected User primaryOwner;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "secondary_owner")
    protected User secondaryOwner;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @Transient
    protected Money penaltyFee = new Money(new BigDecimal("40")); //need to be Money

    protected Date creationDate;

    protected Date interestDate; // --> SERÁ A LIBRE ELECCION DEL ADMIN O DEL USER LA FECHA DE INTERÉS O SERÁ AUTOMÁTICA

    //this must be start just when the new operation is done
    //protected Date transactionDate;  // ---> JUST FOR THE ANTIFRAUD METHOD


    // 1 ACCOUNT == N OPERATIONS
    @OneToMany(mappedBy = "accountSend")
    private List<Operations> operationSend;

    @OneToMany(mappedBy = "accountReceive")
    private List<Operations> operationReceive;


    //changes to money attribute
    public Account(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.status = Status.ACTIVE;
        setCreationDate();
        setInterestDate();
        setOperationSend(new ArrayList<>());
        setOperationReceive(new ArrayList<>());
    }

    public static Account byDTO(AccountDTO accountDTO, User primaryOwner, User Secondary) {

        return new Account(accountDTO.getBalance(), accountDTO.getSecretKey(), primaryOwner, Secondary, accountDTO.getStatus());
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

    public void setOperationSend(List<Operations> operationSend) {
        this.operationSend = operationSend;
    }

    public void setOperationReceive(List<Operations> operationReceive) {
        this.operationReceive = operationReceive;
    }

    /*
    * this method gonna set every value from transactions
    * */
    public void addToOperationSendList(Operations operations){
        if(operationSend.contains(operations)){
            return;
        }

        operationSend.add(operations);
        operations.setAccountSend(this);
    }

    public void addToOperationReceiveList(Operations operations){
        if(operationReceive.contains(operations)){
            return;
        }

        operationSend.add(operations);
        operations.setAccountSend(this);
    }

    /*
    *
    The penaltyFee for all accounts (that have this attribute) should be 40.
    If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically

    * */

    public void penaltyFeeChecker(Money minimum){
        if(getBalance().getAmount().compareTo(minimum.getAmount()) < 0 ){
            this.balance = new Money(getBalance().decreaseAmount(penaltyFee));
            //setBalance(new Money(getBalance().decreaseAmount(penaltyFee)));
        }
    }


    public void addInterestRate(BigDecimal interestRate, InterestType typus){

        switch (typus){
            case MONTHLY -> {
                Date month = (Date) getInterestDate().clone();
                month.setMonth(month.getMonth() + 1);

                //checking if the interest month compare to actual month is the same for add the interest
                if(month.compareTo(new Date()) == 0){
                    setBalance(new Money(getBalance().getAmount().multiply(interestRate)));
                }
            }
            case ANNUALLY -> {
                Date year = (Date) getInterestDate().clone();
                System.out.println(year.getYear());
                year.setYear(year.getYear() + 1);

                System.out.println(year);

                //checking if the interest year compare to actual year is the same for add the interest
                if(year.compareTo(new Date()) == 0){
                    setBalance(new Money(getBalance().getAmount().multiply(interestRate)));
                }

            }
        }
    }
}
