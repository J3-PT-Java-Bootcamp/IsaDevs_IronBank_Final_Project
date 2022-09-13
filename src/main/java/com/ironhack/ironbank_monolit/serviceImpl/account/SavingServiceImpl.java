package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.CreditDTO;
import com.ironhack.ironbank_monolit.dto.accountDTO.SavingDTO;
import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Saving;
import com.ironhack.ironbank_monolit.repository.account.SavingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "saving")
public class SavingServiceImpl implements SavingService {

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Override
    public List<SavingDTO> getAll() {
        var all = savingRepository.findAll();

        List<SavingDTO> listDTO = new ArrayList<>();
        for (var i : all) {
            listDTO.add(SavingDTO.byObject(i));
        }
        return listDTO;
    }

    @Override
    public SavingDTO findById(Long id) {
        var byInstance = savingRepository.findById(id).orElseThrow();
        var byDto = SavingDTO.byObject(byInstance);

        return byDto;
    }

    @Override
    public SavingDTO saveObject(SavingDTO savingDTO) {
        var user1 = accountHolderRepository.findById(savingDTO.getPrimaryOwner()).orElseThrow();
        var user2 = accountHolderRepository.findById(savingDTO.getSecondaryOwner()).orElseThrow();

        var byInstace = Saving.byDTO(savingDTO, user1, user2);

        savingRepository.save(byInstace);

        return SavingDTO.byObject(byInstace);
    }
}
