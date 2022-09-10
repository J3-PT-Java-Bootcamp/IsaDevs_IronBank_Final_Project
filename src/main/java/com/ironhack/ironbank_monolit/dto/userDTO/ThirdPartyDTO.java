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

    private long owner;

    private long secondaryOwner;

    //by class

    private String hashedKey;

    private Money amount;

    private long idAccount;

    private String secretKey;

    public static ThirdPartyDTO byObject(ThirdPartyDTO thirdPartyDTO){

        return new ThirdPartyDTO(thirdPartyDTO.getName(),thirdPartyDTO.getOwner(), thirdPartyDTO.getSecondaryOwner(), thirdPartyDTO.getHashedKey(), thirdPartyDTO.getAmount(), thirdPartyDTO.getIdAccount(), thirdPartyDTO.getSecretKey());
    }
}
