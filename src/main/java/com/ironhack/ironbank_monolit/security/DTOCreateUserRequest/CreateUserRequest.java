package com.ironhack.ironbank_monolit.security.DTOCreateUserRequest;

import lombok.Getter;
import lombok.Setter;

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
}
