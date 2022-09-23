package com.ironhack.ironbank_monolit.ui;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.*;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.serviceImpl.account.CheckingServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.account.CreditServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.account.SavingServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.account.StudentCheckingServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AccountHolderServiceImpl;
import com.ironhack.ironbank_monolit.serviceImpl.user.AdminServiceImpl;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static com.ironhack.ironbank_monolit.model.enums.AccountsType.*;

@Component
public class MenuTest {

    private int response;
    private String password;

    @Autowired
    private OperationServiceImpl operationService;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AdminServiceImpl adminService;


    private Scanner scanner = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("Indique una opcion : \n1.- Ingresar a cuenta \n2.-Crear cuenta");
        var f = scanner.nextInt();

        /*switch (f){
            case 1 -> enterToAccount();
            case 2 -> createAccount();
        }*/
    }

    /*public void enterToAccount() throws Exception {

            System.out.println("desea realizar una transferencia  (1) yes, (2) no");
            response = scanner.nextInt();
            switch (response){
                case 1 -> transfer();
                case 2 -> menu();
            }
        menu();
    }

    public void createAccount() throws Exception {
        /*System.out.println("kind of account?");
        System.out.println("1.- Checking\n 2.-Credit\n 3.-Saving\n 4.-Student");
        response = scanner.nextInt();

        List <Object> values = getAttributes("Name", "date of birth", "street number", "road", "country", "postal code", "mailingaddress");
        var holder = new AccountHolderDTO((String) values.get(0), new Date(1990, Calendar.DECEMBER, 11), Integer.parseInt((String) values.get(2)), (String) values.get(3), (String) values.get(4), Long.valueOf((String) values.get(5)), (String) values.get(6));
        var user = AccountHolder.byDTO(holder);


        System.out.println("user added");
        var user1 = accountHolderRepository.findById(accountHolderRepository.count());

        switch (response){
            case 1 -> { //checking
                List <Object> checking = getAttributes("Balance", "Secret Key");
                adminService.saveNewAccount(holder, "checking", null, null, new Money(new BigDecimal((String) checking.get(0))), (String) checking.get(1));

                System.out.println("New User and Account created" );

            }
            case 2 -> { //credit
                List <Object> credit = getAttributes("Balance", "Secret Key", "creditLimit", "interestRate");

                adminService.saveNewAccount(holder, "credit", new Money(new BigDecimal((String) credit.get(2))), new BigDecimal((String) credit.get(3)),new Money(new BigDecimal((String) credit.get(0))), (String) credit.get(1));

                System.out.println("New User and Account created");
            }
            case 3 -> {//saving
                List <Object> saving = getAttributes("Balance", "Secret Key", "interestRate");
                adminService.saveNewAccount(holder, "saving", null, new BigDecimal((String) saving.get(2)), new Money(new BigDecimal((String) saving.get(0))), (String) saving.get(1));

                System.out.println("New User and Account created");
            }
            case 4 -> {
                List <Object> student = getAttributes("Balance", "Secret Key");
                adminService.saveNewAccount(holder, "student", null, null, new Money(new BigDecimal((String) student.get(0))), (String) student.get(1));

                System.out.println("New User and Account created");
            }
        }*/

      /*  menu();
    }

    public List<Object> getAttributes(Object ... values){
        List <Object> attribute = new ArrayList<>();
        for (var i : values){
            System.out.println(i);
            attribute.add(scanner.next());
        }

        return attribute;
    }

    /*public void transfer() throws Exception {
        System.out.println("id user");
        var idaccount = scanner.nextLong();
        System.out.println("indique id cuenta a transferir?");
        var id = scanner.nextLong();
        System.out.println("name owner?");
        var name = scanner.next();
        System.out.println("monto?");
        var ammount = scanner.nextBigDecimal();

        operationService.transfer(idaccount, id, name, ammount);
    }*/
}
