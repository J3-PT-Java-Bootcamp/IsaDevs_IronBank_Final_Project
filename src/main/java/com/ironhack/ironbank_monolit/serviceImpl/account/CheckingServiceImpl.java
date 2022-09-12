package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "checking")
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;


    @Override
    public List<CheckingDTO> getAll() {
        var all = checkingRepository.findAll();

        List <CheckingDTO> listDTO = new ArrayList<>();
        for(var i : all){
            listDTO.add(CheckingDTO.byObject(i));
        }
        return listDTO;
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

    @Override
    public CheckingDTO findById(Long id) {
        var byInstance = checkingRepository.findById(id).orElseThrow();
        var byDto = CheckingDTO.byObject(byInstance);

        return byDto;
    }

    @Override
    public CheckingDTO saveObject(CheckingDTO checkingDTO) {

        var user1 = accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).orElseThrow();
        var user2 = accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).orElseThrow();

        var byInstace = Checking.byDTO(checkingDTO, user1, user2);

        checkingRepository.save(byInstace);
        return CheckingDTO.byObject(byInstace);
    }


}
