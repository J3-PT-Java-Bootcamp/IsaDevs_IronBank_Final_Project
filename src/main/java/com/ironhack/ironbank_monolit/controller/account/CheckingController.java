package com.ironhack.ironbank_monolit.controller.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.serviceImpl.account.CheckingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/checking-accounts")
public class CheckingController {

    @Autowired
    @Qualifier("checking")
    private CheckingServiceImpl checkingService;


    @GetMapping("/status/{status}")
    public List<Checking> getStatus(@PathVariable("status") String status){
        return checkingService.getStatus(status);
    }
}
