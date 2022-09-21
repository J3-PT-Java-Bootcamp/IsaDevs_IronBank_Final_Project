package com.ironhack.ironbank_monolit.security.dto.roles;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class AccountHolderSecDTO {
    private String name;
    private Date dateOfBirth;
    private Integer number;
    private String road;
    private String country;
    private Long postalCode;
    private String mailingAddress;
}
