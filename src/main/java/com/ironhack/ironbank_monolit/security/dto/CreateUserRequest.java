package com.ironhack.ironbank_monolit.security.dto;

import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CreateUserRequest {

    /*
    * THIS IS LIKE THE DTO FOR USERREQUEST, IN THIS CLASS WE GONNA PUT ALL ATTRIBUTTES THAT WE NEED IN THE BUILD INSTANCE
    * */

    String username;
    String password;
    String email;
    String firstname;
    String lastname;
    String rol;

    //***************************************

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

    // private long primaryOwner;
    private User primaryOwner;

    private long secondaryOwner;

    //private Status status;

    private BigDecimal interestRate;

    private BigDecimal creditLimit;

    private String accountType;
}
