package com.ironhack.ironbank_monolit.controller.user;

import com.ironhack.ironbank_monolit.dto.accountDTO.AccountDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.security.DTOCreateUserRequest.CreateUserRequest;
import com.ironhack.ironbank_monolit.security.DTOCreateUserRequest.LoginRequest;
import com.ironhack.ironbank_monolit.security.config.KeyCloakProvider;
import com.ironhack.ironbank_monolit.security.service.KeycloakAdminClientService;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AdminController {

    @Autowired
    @Qualifier("account-holder")
    private AccountHolderServiceImpl accountHolderService;

    @Autowired
    @Qualifier("security")
    private KeycloakAdminClientService adminClientService;

    @Autowired
    @Qualifier("admin")
    private AdminServiceImpl adminService;


    @Autowired
    @Qualifier("operations")
    private OperationServiceImpl operationService;

    private final KeyCloakProvider keyCloakProvider;

    public AdminController(KeyCloakProvider keyCloakProvider){
        this.keyCloakProvider = keyCloakProvider;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> findAll(){

        var x = adminService.getAll();

        //System.out.println(x);

        return x;
    }

    @GetMapping("/checking")
    public List <CheckingDTO> findAllChecking(){
        return adminService.getAllChecking();
    }
    @GetMapping("/credits")
    public List <CreditDTO> findAllCredit(){
        return adminService.getAllCredit();
    }

    @GetMapping("/users")
    public List <AccountHolderDTO> all(){

        return accountHolderService.holders();
    }

    //*********  DONT WORK WITH REPOSITORY
    @GetMapping("/users-total")
    public List <AccountHolder> total(){

        var result = accountHolderService.total();

        System.out.println(result);
        return result;
    }

    //**********************************

    @GetMapping("/user-balance/{id}")
    public Money getMyBalance(@PathVariable("id") long id) throws Exception {

        return operationService.checkBalance(id);
    }

    @GetMapping("/id-user/{id}")
    public AccountHolderDTO findById(@PathVariable("id") long id){
        return accountHolderService.byId(id);
    }
    //********** ADD NEW REGISTER TO DATABASE

    @PostMapping("/add-new-register")
    public AccountHolderDTO createNewAccount(@RequestBody NewRegisterDTO newRegisterDTO){

        AccountHolderDTO newUser = new AccountHolderDTO(newRegisterDTO.getName(), newRegisterDTO.getDateOfBirth(), newRegisterDTO.getNumber(), newRegisterDTO.getRoad(), newRegisterDTO.getCountry(), newRegisterDTO.getPostalCode(), newRegisterDTO.getMailingAddress());
        System.out.println(newUser);

        var interest = newRegisterDTO.getInterestRate();

        var balan = new Money(newRegisterDTO.getBalance());



        var c = adminService.saveNewAccount(newUser, newRegisterDTO.getAccountType(),new Money(newRegisterDTO.getCreditLimit()), interest, balan, newRegisterDTO.getSecretKey());

        System.out.println(c);
        return c;
    }


    //*******************************************************************************
    //  FOR SECURITY

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) { // CALL TO THE DTO FROM SECURITY EMULING LIKE THE DTO FOR CREATE A NEW ACCOUNT
       // try (Response createdResponse = adminClientService.createKeycloakUser(user)) {
        Response createdResponse = adminClientService.createKeycloakUser(user);
            return ResponseEntity.status(createdResponse.getStatus()).build();
        //}
    }


    //THIS POST GET THE TOKEN AND UPDATE EVERYTIME
    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest){
        Keycloak keycloak = keyCloakProvider.createKeycloakInstanceWithCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;

        try{
            accessTokenResponse = keycloak.tokenManager().getAccessToken();

            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }


    //******************************************************************************************

    //********** OPERATIONS AREA

    @PatchMapping("/id-user/{idUser}/id/{id}/name/{name}/ammount/{ammount}")
    public Account makeAtransfer(@PathVariable("idUser") long idUser, @PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("ammount")BigDecimal ammount) throws Exception {
        return operationService.transfer(idUser, id, name, ammount);
    }


}
