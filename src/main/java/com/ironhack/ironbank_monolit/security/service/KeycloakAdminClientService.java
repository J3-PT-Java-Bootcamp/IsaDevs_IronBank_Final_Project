package com.ironhack.ironbank_monolit.security.service;

import com.ironhack.ironbank_monolit.security.DTOCreateUserRequest.CreateUserRequest;
import com.ironhack.ironbank_monolit.security.config.KeyCloakProvider;
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
    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    //instance without autowired
    public KeycloakAdminClientService(KeyCloakProvider keyCloakProvider){
        this.keyProvider = keyCloakProvider;
    }

    //this method gonna update the values from crentials
    private static CredentialRepresentation createPasswordCredentials(String password){
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);

        return passwordCredential;
    }

    //RECEIVE THE REQUEST FROM POSTMAN AND SET THE VALUES AND THE DTO USERREQUEST
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

        kcUser.setGroups(List.of("admin")); // ADD EVERY MEMBER TO THE GROUP MEMBER IN THE KEYCLOAK CLIENT

        Response response = usersResource.create(kcUser);

        //VERIFIED THE RESPONSE

        //ONLY IF THE USER WAS TRANSFER TO A LIST
        if(response.getStatus() == 201){
            List <UserRepresentation> userRepresentationList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRepresentation -> userRepresentation.getUsername().equals(kcUser.getUsername())).toList();

            var createdUser = userRepresentationList.get(0);
            log.info("User with id " + createdUser.getId() + " was correctly created");
        }

        return response;
    }

}
