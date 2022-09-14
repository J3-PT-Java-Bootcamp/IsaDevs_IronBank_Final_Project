package com.ironhack.ironbank_monolit.model.account;
import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checking extends Account{


    //@DecimalMin(value = "250")
   // private Money MINIMAL_BALANCE;
    @Transient
    private Money MINIMAL_BALANCE = new Money(new BigDecimal("250"));


    //private Money MONTHLY_MAINTENANCE_FEE;
    @Transient
    private Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12"));


    //this gonna be charges by DTO
    public Checking(Money balance, String secretKey, User primaryOwner, User secondaryOwner, Status status,User accounts) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, accounts);
    }

    public static Checking byDTO(CheckingDTO checkingDTO, User primaryOwner, User Secondary) {

        return new Checking(checkingDTO.getBalance(), checkingDTO.getSecretKey(), primaryOwner, Secondary, checkingDTO.getStatus(),null);
    }


    @Override
    public void setBalance(Money balance){
        //call to set the super attributte AND THE SETTING THE BALANCE WITH THE PENALTYFEE CHECKING FOR EVERY CLASS
        super.setBalance(balance);
       // super.penaltyFeeChecker(MINIMAL_BALANCE);
        monthlyDeduction(balance);

    }
    public void monthlyDeduction(Money balance){

        if(super.getCreationDate().getDay() == new Date().getDay()){
            super.setBalance(new Money(getBalance().decreaseAmount(MONTHLY_MAINTENANCE_FEE)));
        }
    }

}
