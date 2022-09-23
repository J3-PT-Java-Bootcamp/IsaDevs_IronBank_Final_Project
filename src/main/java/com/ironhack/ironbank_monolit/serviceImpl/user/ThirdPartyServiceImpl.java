package com.ironhack.ironbank_monolit.serviceImpl.user;

import com.ironhack.ironbank_monolit.dto.userDTO.ThirdPartyDTO;
import com.ironhack.ironbank_monolit.model.user.ThirdParty;
import com.ironhack.ironbank_monolit.repository.user.ThirdPartyRepository;
import com.ironhack.ironbank_monolit.service.user.ThirdPartyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "third-party")
public class ThirdPartyServiceImpl implements ThirdPartyService {

    private final ThirdPartyRepository partyRepository;

    public ThirdPartyServiceImpl(ThirdPartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    //************************************************************************************
    // THIS METHOD CONVERT ALL REGISTER IN THE DATABASE TO A DTO FOR VIEWS
    //************************************************************************************
    @Override
    public List<ThirdPartyDTO> total() {
        var parties = partyRepository.findAll();
        List <ThirdPartyDTO> thirdPartyDTOS = new ArrayList<>();

        for(var i : parties){
            thirdPartyDTOS.add(ThirdPartyDTO.byObject(i));
        }
        return thirdPartyDTOS;
    }

    //************************************************************************************
    // THIS METHOD RECEIVE A DTO THIRDPARTY FROM POSTMAN AND CONVERT TO INSTANCE FOR SAVE IN THE DATABASE
    //************************************************************************************
    @Override
    public ThirdPartyDTO saveThirdPart(ThirdPartyDTO thirdPartyDTO) {
        var instance = ThirdParty.byDTORegister(thirdPartyDTO);
        partyRepository.save(instance);

        return thirdPartyDTO;
    }

    @Override
    public void deleteThirdParty(Long id) {
        partyRepository.deleteById(id);
    }
}
