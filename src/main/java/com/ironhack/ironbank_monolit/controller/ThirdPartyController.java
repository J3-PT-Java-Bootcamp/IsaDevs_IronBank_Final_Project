package com.ironhack.ironbank_monolit.controller;

import com.ironhack.ironbank_monolit.dto.registerDTO.NewRegisterDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.service.user.ThirdPartyService;
import com.ironhack.ironbank_monolit.validation.OperationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/third-party")
public class ThirdPartyController {

    private final ThirdPartyService partyService;

    private final OperationServiceImpl operationService;

    public ThirdPartyController(ThirdPartyService partyService, OperationServiceImpl operationService) {
        this.partyService = partyService;
        this.operationService = operationService;
    }


    @PatchMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account makeAtransfer(@RequestBody NewRegisterDTO newRegisterDTO) throws Exception {

        return operationService.transfer(newRegisterDTO.getPrimary(), newRegisterDTO.getId(), newRegisterDTO.getName(), newRegisterDTO.getBalance(), newRegisterDTO.getRol(), newRegisterDTO.getSecretId());
    }
}
