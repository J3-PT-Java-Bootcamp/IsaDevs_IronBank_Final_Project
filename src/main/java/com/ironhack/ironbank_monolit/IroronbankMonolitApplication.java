package com.ironhack.ironbank_monolit;

import com.ironhack.ironbank_monolit.ui.MenuTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IroronbankMonolitApplication implements CommandLineRunner {
    //public class IroronbankMonolitApplication{

    @Autowired
    private MenuTest menuTest;
    public static void main(String[] args) {
        SpringApplication.run(IroronbankMonolitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menuTest.menu();
    }
}
