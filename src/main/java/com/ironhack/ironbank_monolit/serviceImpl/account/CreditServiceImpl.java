package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.repository.account.CreditRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "credit")
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Override
    public List<CreditDTO> getAll() {
        var all = creditRepository.findAll();

        List<CreditDTO> listDTO = new ArrayList<>();
        for (var i : all) {
            listDTO.add(CreditDTO.byObject(i));
        }
        return listDTO;
    }

    @Override
    public CreditDTO findById(Long id) {
        var byInstance = creditRepository.findById(id).orElseThrow();
        var byDto = CreditDTO.byObject(byInstance);

        return byDto;
    }

    @Override
    public CreditDTO saveObject(CreditDTO creditDTO) {
        var user1 = accountHolderRepository.findById(creditDTO.getPrimaryOwner()).orElseThrow();
        var user2 = accountHolderRepository.findById(creditDTO.getSecondaryOwner()).orElseThrow();

        var byInstace = Credit.byDTO(creditDTO, user1, user2);

        creditRepository.save(byInstace);
        return CreditDTO.byObject(byInstace);
    }
}
