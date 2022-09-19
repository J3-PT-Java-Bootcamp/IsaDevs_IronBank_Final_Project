package com.ironhack.ironbank_monolit.security.config;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Getter
public class KeyCloakProvider {

    //credentials constants to use in the properties

    private static Keycloak instanceOfKeycloak = null;

    @Value("${keycloak.auth-server-url}")
    public String serverURL;//call to server constant

    @Value("${keycloak.realm}")
    public String realm;

    @Value("${keycloak.resource}")
    public String clientID;

    @Value("${keycloak.credentials.secret}")
    public String clientSecret;

    public KeyCloakProvider() {
    }

    //construction of the keycloak instance

    public Keycloak getInstance(){
        if(instanceOfKeycloak == null){

            return KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(clientID)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        }

        return instanceOfKeycloak;
    }


    //THIS METHOD FUNTIONALLY LIKE THE DTO WITH THE BUILDER, retunrnig a keycloak instance with that requirements
    public KeycloakBuilder createKeycloakInstanceWithCredentials(String username, String password){

        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverURL)
                .clientId(clientID)
                .clientSecret(clientSecret)
                .username(username)
                .password(password);
    }

    //this method gonna take values from JSON POSTMAN

    public JsonNode refreshToken(String refresh) throws UnirestException {

        //we need construct every value for refresh the token automatically
        String url = serverURL + "/realms/" + realm + "/protocol/openid-connect/token";

        return Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", clientID)
                .field("client_secret", clientSecret)
                .field("refresh_token", refresh)
                .field("grant_type", "refresh_token")
                .asJson().getBody();
    }
}
