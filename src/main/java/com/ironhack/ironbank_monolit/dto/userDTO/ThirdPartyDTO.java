package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.user.ThirdParty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class ThirdPartyDTO {

    private String name;

    private String secretKey;

    private String username;

    private String secret;
    //by class

    private String hashedKey;

    private Money amount;

    private long idAccount;

    public ThirdPartyDTO(String name, String secretKey, String username, String secret, String hashedKey, Money amount, long idAccount) {
        this.name = name;
        this.secretKey = secretKey;
        this.username = username;
        this.secret = secret;
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
    }

    public static ThirdPartyDTO byObject(ThirdParty thirdPartyDTO){

        return new ThirdPartyDTO(thirdPartyDTO.getName(),thirdPartyDTO.getSecretKey(), thirdPartyDTO.getUserName(),  thirdPartyDTO.getSecret(), thirdPartyDTO.getHashedKey(), thirdPartyDTO.getAmount(), thirdPartyDTO.getIdAccount());
    }
}
