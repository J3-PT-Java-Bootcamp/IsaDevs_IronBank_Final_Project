package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckingDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    private long accounts;

    private Money  minimalBalance;

    private Money  monthlyMaintenanceFee;



    public static CheckingDTO byObject(Checking checking){

        return new CheckingDTO(checking.getBalance(),checking.getSecretKey(), checking.getPrimaryOwner().getId(), checking.getSecondaryOwner().getId(), checking.getStatus(), 1, checking.getMINIMAL_BALANCE(),checking.getMONTHLY_MAINTENANCE_FEE());
    }


}
