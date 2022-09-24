package com.ironhack.ironbank_monolit.controller.user;

import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.ThirdPartyDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.security.dto.CreateUserRequest;
import com.ironhack.ironbank_monolit.security.dto.LoginRequest;
import com.ironhack.ironbank_monolit.security.config.KeyCloakProvider;
import com.ironhack.ironbank_monolit.security.service.KeycloakAdminClientService;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import com.ironhack.ironbank_monolit.validation.Operations;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AccountHolderServiceImpl accountHolderService;

    private final KeycloakAdminClientService adminClientService;

    private final AdminServiceImpl adminService;

    private final  OperationServiceImpl operationService;

    private final KeyCloakProvider keyCloakProvider;


    public AdminController(AccountHolderServiceImpl accountHolderService, KeycloakAdminClientService adminClientService, AdminServiceImpl adminService, OperationServiceImpl operationService, KeyCloakProvider keyCloakProvider){
        this.accountHolderService = accountHolderService;
        this.adminClientService = adminClientService;
        this.adminService = adminService;
        this.operationService = operationService;
        this.keyCloakProvider = keyCloakProvider;
    }


    //**************************************************************************

//              this method returning a list of all accounts in the database, we need to pass the "type"

    // **************************************************************************


    @GetMapping("/get-account/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAType(@PathVariable String type){

        return adminService.getTotal(type);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List <AccountHolderDTO> all(){

        return accountHolderService.holders();
    }

    @GetMapping("/total-admins")
    @ResponseStatus(HttpStatus.OK)
    public List<AdminDTO> getAdmins(){
        return adminService.getAdmins();
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List <AdminDTO> byName(@PathVariable("name") String name){
        return adminService.getByName(name);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDTO byUserName(@PathVariable("username") String username){
        return adminService.getByUserName(username);
    }

    @GetMapping("/account-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getMyBalance(@PathVariable("id") Long  id) throws Exception {

        return adminService.getBalanceByAccount(id);
    }

    @GetMapping("/typus/{types}/account-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getByAccountId (@PathVariable("types") String types, @PathVariable("id") Long  id) throws Exception {

        return adminService.getAccountById(types, id);
    }

    @GetMapping("/third-party")
    @ResponseStatus(HttpStatus.OK)
    public List <ThirdPartyDTO> total(){
        return adminService.total();
    }

    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.OK)
    public List <Operations> gettotal(){
        return adminService.getTotal();
    }

    @GetMapping("/user-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List <Money> getMyBalances(@PathVariable("id") Long  id) throws Exception {

        return adminService.getBalanceByUser(id);
    }

    @GetMapping("/id-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolderDTO findById(@PathVariable("id") long id){
        return accountHolderService.byId(id);
    }

    @PostMapping("/add-new-register")
    public AccountHolderDTO createNewAccount(@RequestBody NewRegisterDTO newRegisterDTO) throws Exception {

        var interest = newRegisterDTO.getInterestRate();

        var balan = new Money(newRegisterDTO.getBalance());

        return adminService.addNewAccount(newRegisterDTO.getPrimary(), newRegisterDTO.getAccountType(),new Money(newRegisterDTO.getCreditLimit()), interest, balan, newRegisterDTO.getSecretKey());
    }

    //*******************************************************************************
    //  FOR SECURITY
    //*******************************************************************************


    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) { // CALL TO THE DTO FROM SECURITY EMULING LIKE THE DTO FOR CREATE A NEW ACCOUNT
        // with this we get the id
        Response createdResponse = adminClientService.createKeycloakUser(user);

        return ResponseEntity.status(createdResponse.getStatus()).build();

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
    //OPERATIONS AREA
    //******************************************************************************************

    @PatchMapping("/make-a-transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account makeAtransfer(@RequestBody NewRegisterDTO newRegisterDTO) throws Exception {

        return operationService.transfer(newRegisterDTO.getPrimary(), newRegisterDTO.getId(), newRegisterDTO.getName(), newRegisterDTO.getBalance(), newRegisterDTO.getRol(), newRegisterDTO.getSecretId());
    }

    @PatchMapping("modify/id/{id}/ammount/{ammount}")
    public Account modifyBalance(@PathVariable("id") Long id, @PathVariable("ammount")BigDecimal ammount){
        return adminService.modifyBalance(id, ammount);
    }


    @DeleteMapping("/delete-account/{id}")
    public void deleteAccount(@PathVariable("id") Long id) throws Exception {
        adminService.deleteAccount(id);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws Exception {
        adminService.deleteUser(id);
    }
}
