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

    private BigDecimal balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    private long accounts;

    private BigDecimal  minimalBalance;

    private BigDecimal  monthlyMaintenanceFee;


    public static CheckingDTO byObject(Checking checking){

        return new CheckingDTO(checking.getBalance().getAmount(),checking.getSecretKey(), checking.getPrimaryOwner().getId(), checking.getSecondaryOwner().getId(), checking.getStatus(), checking.getAccounts().getId(), checking.getMINIMAL_BALANCE().getAmount(),checking.getMONTHLY_MAINTENANCE_FEE().getAmount());
    }


}
