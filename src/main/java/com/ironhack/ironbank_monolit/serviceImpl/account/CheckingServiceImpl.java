package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import com.ironhack.ironbank_monolit.service.account.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "checking")
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;


    @Override
    public List<CheckingDTO> getAll() {
        return null;
    }

    @Override
    public List<CheckingDTO> getStatus(String stats) {
        var checkStatus = checkingRepository.findByStatus(Status.valueOf(stats.toUpperCase()));

        List <CheckingDTO> listDTO = new ArrayList<>();
        for(var i : checkStatus){
            listDTO.add(CheckingDTO.byObject(i));
        }
        return listDTO;
    }


}
