package com.ironhack.ironbank_monolit.service.user;

import com.ironhack.ironbank_monolit.dto.userDTO.ThirdPartyDTO;

import java.util.List;

public interface ThirdPartyService {

    List<ThirdPartyDTO> total();

    ThirdPartyDTO saveThirdPart(ThirdPartyDTO thirdPartyDTO);

    void deleteThirdParty(Long id);
}
