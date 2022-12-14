package com.ironhack.ironbank_monolit.security.service;

import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.model.user.ThirdParty;
import com.ironhack.ironbank_monolit.security.dto.CreateUserRequest;
import com.ironhack.ironbank_monolit.security.config.KeyCloakProvider;
import com.ironhack.ironbank_monolit.security.rol.Rol;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Log
@Service(value = "security")
public class KeycloakAdminClientService {

    private final KeyCloakProvider keyProvider;
    private final AdminServiceImpl adminService;

    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    //instance without autowired
    public KeycloakAdminClientService(KeyCloakProvider keyCloakProvider, AdminServiceImpl adminService){
        this.keyProvider = keyCloakProvider;
        this.adminService = adminService;
    }

    //this method gonna update the values from crentials
    private static CredentialRepresentation createPasswordCredentials(String password){
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);

        return passwordCredential;
    }

    //************************************************************************************
    // RECEIVE THE REQUEST FROM POSTMAN AND SET THE VALUES WITH THE DTO USERREQUEST VARIABLES, THIS IS TOTALLY CUSTOMIZED
    //************************************************************************************

    public Response createKeycloakUser(CreateUserRequest userRequest){

        //SAVE THE METHOD FROM THE KEY PROVIDER INTO A NEW VARIABLE
        var adminKeycloak = keyProvider.getInstance();
        UsersResource usersResource = keyProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(userRequest.getPassword()); //from the DTO

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(userRequest.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(userRequest.getFirstname());
        kcUser.setLastName(userRequest.getLastname());
        kcUser.setEmail(userRequest.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        //************************************************************************************
        // WITH THE DTO VALUE CHECK THE GROUP AND ADD THERE
        //************************************************************************************

        var kcGruopToGo = userRequest.getRol();


        switch (kcGruopToGo.toUpperCase()){
            case "MEMBER" -> kcUser.setGroups(List.of("member"));
            case "ADMIN" -> kcUser.setGroups(List.of("admin"));
            default -> kcUser.setGroups(List.of("moderator"));
        }

        //kcUser.setGroups(List.of("admin")); // ADD EVERY MEMBER TO THE GROUP MEMBER IN THE KEYCLOAK CLIENT

        //************************************************************************************
        // 1 .- BUILD A Response OBJECT WITH THE USERSRESOURSE CREATED FROM THE USERREPRESENTATION SETTING FROM DTO VALUES
        // 2 .- CHECKING THE CODE STATUS FROM THE RESPONSE OBJECT.
        // 3 .- ADD THE KEYCLOAK USER TO A LIST OF USERREPRESENTATION FOR TRANSFORM THAT VALUES TO THE DATABASE (PARSING)
        // 4 .- ADD THE OBJECT FROM REPRESENTATION LIST TO A NEW VARIABLE FOR MANIPULATED AND BUILD IN THE DATABASE AND CLASS
        // 5 .- WITH THAT VARIABLE TAKE THE VALUE FOR THE KEYCLOAK UNIQUE ID TO OUR DATABASE AND SPECIFIC USER
        // 6 .- AFTER WE BUILD THE OBJECT CLASS, WE JUST CALL OUR SERVICE TO SAVE THAT OBJECT WITH THAR PARAMS IN THE DATABASE
        //************************************************************************************


        Response response = usersResource.create(kcUser);

        //ONLY IF THE USER WAS TRANSFER TO A LIST
        if(response.getStatus() == 201){
            List <UserRepresentation> userRepresentationList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRepresentation -> userRepresentation.getUsername().equals(kcUser.getUsername())).toList();

            var createdUser = userRepresentationList.get(0);
            log.info("User with id " + createdUser.getId() + " was correctly created");

            AccountHolderDTO newUser = new AccountHolderDTO(userRequest.getName(), createdUser.getId(), createdUser.getUsername(), userRequest.getDateOfBirth(), userRequest.getNumber(), userRequest.getRoad(), userRequest.getCountry(), userRequest.getPostalCode(), userRequest.getMailingAddress());
            System.out.println(newUser);

            var interest = userRequest.getInterestRate();

            var balan = new Money(userRequest.getBalance());

            var accountHolderToSave = adminService.saveNewAccount(newUser, userRequest.getAccountType(),new Money(userRequest.getCreditLimit()), interest, balan, userRequest.getSecretKey());

            System.out.println(accountHolderToSave);

        }

        return response;
    }

}
