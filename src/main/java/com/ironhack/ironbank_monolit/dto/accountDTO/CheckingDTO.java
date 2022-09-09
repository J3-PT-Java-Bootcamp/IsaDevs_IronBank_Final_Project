package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckingDTO {

    private Money balance;

    private String secretKey;

    private User primaryOwner;

    private User secondaryOwner;

    private Status status;

    private User accounts;

    private Money  minimalBalance;

    private Money  monthlyMaintenanceFee;


    public static CheckingDTO byObject(Checking checking){
        var dtoChecker = new CheckingDTO();
        dtoChecker.setBalance(checking.getBalance());
        dtoChecker.setSecretKey(checking.getSecretKey());
        dtoChecker.setPrimaryOwner(checking.getPrimaryOwner());
        dtoChecker.setSecondaryOwner(checking.getSecondaryOwner());
        dtoChecker.setStatus(checking.getStatus());
        dtoChecker.setAccounts(checking.getAccounts());
        dtoChecker.setMinimalBalance(checking.getMINIMAL_BALANCE());
        dtoChecker.setMonthlyMaintenanceFee(checking.getMONTHLY_MAINTENANCE_FEE());

        return dtoChecker;
    }


}
