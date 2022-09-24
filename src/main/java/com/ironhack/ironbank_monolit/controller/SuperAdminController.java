package com.ironhack.ironbank_monolit.controller;

import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.security.dto.CreateUserRequest;
import com.ironhack.ironbank_monolit.security.dto.LoginRequest;
import com.ironhack.ironbank_monolit.security.config.KeyCloakProvider;
import com.ironhack.ironbank_monolit.security.service.KeycloakAdminClientService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/super-admin")
public class SuperAdminController {

    // CALL TO THE KEYCLOAK SECURITY CLASS
    private final KeyCloakProvider provider;

    private final KeycloakAdminClientService clientService;



    public SuperAdminController(KeyCloakProvider provider, KeycloakAdminClientService clientService){

        this.provider = provider;
        this.clientService = clientService;
    }

    //*******************************************************************************
    //  FOR SECURITY

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) { // CALL TO THE DTO FROM SECURITY EMULING LIKE THE DTO FOR CREATE A NEW ACCOUNT
        // with this we get the id
        Response createdResponse = clientService.createKeycloakUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }


    //THIS POST GET THE TOKEN AND UPDATE EVERYTIME
    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest){
        Keycloak keycloak = provider.createKeycloakInstanceWithCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;

        try{
            accessTokenResponse = keycloak.tokenManager().getAccessToken();

            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }



    //******************************************************************************************
}
