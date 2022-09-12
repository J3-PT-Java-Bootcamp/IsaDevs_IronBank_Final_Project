package com.ironhack.ironbank_monolit.ui;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import com.ironhack.ironbank_monolit.repository.account.StudentCheckingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.StudentCheckingService;
import com.ironhack.ironbank_monolit.serviceImpl.account.CheckingServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.ironhack.ironbank_monolit.model.enums.AccountsType.*;

@Component
public class MenuTest {

    private int response;
    private String user;
    private String password;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private CheckingServiceImpl checkingService;

    @Autowired
    private StudentCheckingService studentCheckingService;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderServiceImpl accountHolderService;

    private Scanner scanner = new Scanner(System.in);

    public void menu(){
        System.out.println("Indique una opcion : \n1.- Ingresar a cuenta \n2.-Crear cuenta");
        var f = scanner.nextInt();

        switch (f){
            case 1 -> enterToAccount();
            case 2 -> createAccount();
        }
    }

    public void enterToAccount(){
        System.out.println("user");
        user = scanner.next();
        //System.out.println("password");
        password = "cATYcAT";

        if(!accountHolderService.findByPassAnU("cATYcAT", "pedro perez").isEmpty()){
            System.out.println("welcome");
        }else{
            System.out.println("try again");
        }

    }

    public void createAccount(){
        System.out.println("kind of account?");
        System.out.println("1.- Checking\n 2.-Credit\n 3.-Saving\n 4.-Student");
        user = scanner.next();

        //"owner", "secondary Owner", "accounts",
        //List <Object> values = getAttributes("Name", "date of birth", "street number", "road", "country", "postal code", "mailingaddress");
        List <Object> values = getAttributes("Name", "date of birth", "street number", "road", "country", "postal code", "mailingaddress");
        var holder = new AccountHolderDTO((String) values.get(0), new Date((String) values.get(1)), Integer.parseInt((String) values.get(2)), (String) values.get(3), (String) values.get(4), Long.valueOf((String) values.get(5)), (String) values.get(6));
        accountHolderService.save(holder);

        System.out.println("user added");
        var user1 = accountHolderRepository.findById(accountHolderRepository.count());
        var num = scanner.nextInt();
        switch (num){
            case 1 -> {
                //((String) checking.get(2)))
                List <Object> checking = getAttributes("Balance", "Secret Key", "Status", "minimal balance", "fee");
                var check = AccountHolder.primaryOwnerVerified(CHECKING, new Money(new BigDecimal((String) checking.get(0))), (String) checking.get(1), user1.orElseThrow(), user1.orElseThrow(), Status.ACTIVE , user1.orElseThrow(), new Money(new BigDecimal((String) checking.get(3))), new Money(new BigDecimal((String) checking.get(4))), null, null, user1.get().getDateOfBirth());
                var dtoChecking = CheckingDTO.byObject((Checking) check);
                checkingService.saveObject(dtoChecking);

                System.out.println("New User and Account created");
                //(CHECKING, accountHolderRepository.count(), accountHolderRepository.count(), checking.get(0), checking.get(1), checking.get(2), accountHolderRepository.count(), checking.get(3), checking.get(4), null, null);

            }
            case 2 -> System.out.println("not yey");
            case 3 -> System.out.println("not yey");
            case 4 -> {
                List <Object> student = getAttributes("Balance", "Secret Key", "Status", "minimal balance", "fee");
                //var check = AccountHolder.primaryOwnerVerified(STUDENT, new Money(new BigDecimal((String) student.get(0))), (String) student.get(1), user1.orElseThrow(), user1.orElseThrow(), Status.ACTIVE , user1.orElseThrow(), new Money(new BigDecimal((String) student.get(3))), new Money(new BigDecimal((String) student.get(4))), null, null);
                //var dtoChecking = StudentCheckingDTO.byObject((StudentChecking) check);
                //studentCheckingService.saveObject(dtoChecking);

                System.out.println("New User and Account created");
            }
        }
    }

    public List<Object> getAttributes(Object ... values){
        List <Object> attribute = new ArrayList<>();
        for (var i : values){
            System.out.println(i);
            attribute.add(scanner.next());
        }

        return attribute;
    }

}
