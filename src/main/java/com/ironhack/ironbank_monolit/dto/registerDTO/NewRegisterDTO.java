package com.ironhack.ironbank_monolit.dto.registerDTO;

import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewRegisterDTO {
    private long id;

    private String name;
    private String secretId;
    private String userName;
    //by class

    private Date dateOfBirth;


    private Integer number;
    private String road;
    private String country;
    private Long postalCode;

    private String mailingAddress;

    private BigDecimal balance;

    private String secretKey;

    private long primary;

    private User primaryOwner;

    private long secondaryr;

    private Status status;

    private BigDecimal interestRate;

    private BigDecimal creditLimit;

    private String accountType;


    private String rol;

}
