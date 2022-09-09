package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDTO {

    private String name;

    private Account owner;

    private Account secondaryOwner;

    private List<Account> accountList;

    //by class

    private String hashedKey;

    private Money amount;

    private long idAccount;

    private String secretKey;

    public static ThirdPartyDTO byObject(ThirdPartyDTO thirdPartyDTO){
        var dtoThirdParty = new ThirdPartyDTO();
        dtoThirdParty.setName(thirdPartyDTO.getName());
        dtoThirdParty.setOwner(thirdPartyDTO.getOwner());
        dtoThirdParty.setSecondaryOwner(thirdPartyDTO.getSecondaryOwner());
        dtoThirdParty.setHashedKey(thirdPartyDTO.getHashedKey());
        dtoThirdParty.setAmount(thirdPartyDTO.getAmount());
        dtoThirdParty.setIdAccount(thirdPartyDTO.getIdAccount());
        dtoThirdParty.setSecretKey(thirdPartyDTO.getSecretKey());


        return dtoThirdParty;
    }
}
