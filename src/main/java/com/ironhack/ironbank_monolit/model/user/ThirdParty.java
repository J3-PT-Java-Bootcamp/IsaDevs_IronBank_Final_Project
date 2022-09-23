package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.dto.userDTO.ThirdPartyDTO;
import com.ironhack.ironbank_monolit.model.account.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty extends User {

    @Size(min = 24, max = 24, message = "the hashed key must be with a 24 length extension")
    private String hashedKey;

    @NotBlank // --> difference between @NotBlank and @NotEmpty??
    @NotNull
    private Money amount;

    @NotBlank
    private long idAccount;

    @NotNull
    private String secretKey;

    //************************************************************************************
    // CONSTRUCTOR FOR THIRD PARTY when receive and send money, REGISTER IN THE DATABASE
    //************************************************************************************
    public ThirdParty(String name, String secret, String userName, String hashedKey, Money amount, long idAccount, String secretKey) {
        super(name, secret, userName);
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
        this.secretKey = secretKey;
    }

    //************************************************************************************
    // CONSTRUCTOR FOR THIRD PARTY JUST MAKING A TRANSFER
    //************************************************************************************

    public ThirdParty(String name, String hashedKey, Money amount, long idAccount, String secretKey) {
        super(name);
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
        this.secretKey = secretKey;
    }

    //************************************************************************************
    // CONSTRUCTOR WITH DTO FOR MAKE A TRANSFER
    //************************************************************************************
    public static ThirdParty byDTO(ThirdPartyDTO thirdPartyDTO){
        return  new ThirdParty(thirdPartyDTO.getName(), thirdPartyDTO.getHashedKey(), thirdPartyDTO.getAmount(), thirdPartyDTO.getIdAccount(), thirdPartyDTO.getSecretKey());
    }

    //************************************************************************************
    // CONSTRUCTOR WITH DTO FOR MAKE AND RECEIVE A TRANSFER
    //************************************************************************************
    public static ThirdParty byDTORegister(ThirdPartyDTO thirdPartyDTO){
        return new ThirdParty(thirdPartyDTO.getName(), thirdPartyDTO.getSecret(), thirdPartyDTO.getUsername(), thirdPartyDTO.getHashedKey(), thirdPartyDTO.getAmount(), thirdPartyDTO.getIdAccount(), thirdPartyDTO.getSecretKey());
    }
}
