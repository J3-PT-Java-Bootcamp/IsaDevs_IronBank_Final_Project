package com.ironhack.ironbank_monolit.serviceImpl.account;

import com.ironhack.ironbank_monolit.dto.accountDTO.StudentCheckingDTO;
import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import com.ironhack.ironbank_monolit.repository.account.StudentCheckingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import com.ironhack.ironbank_monolit.service.account.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "student")
public class StudentCheckingServiceImpl implements StudentCheckingService {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;


    @Override
    public List<StudentCheckingDTO> getAll() {
        var all = studentCheckingRepository.findAll();

        List <StudentCheckingDTO> listDTO = new ArrayList<>();
        for(var i : all){
            listDTO.add(StudentCheckingDTO.byObject(i));
        }
        return listDTO;
    }

    @Override
    public StudentCheckingDTO findById(Long id) {
        var byInstance = studentCheckingRepository.findById(id).orElseThrow();
        var byDto = StudentCheckingDTO.byObject(byInstance);

        return byDto;
    }

    @Override
    public StudentCheckingDTO saveObject(StudentCheckingDTO studentCheckingDTO) {

        var user1 = accountHolderRepository.findById(studentCheckingDTO.getPrimaryOwner()).orElseThrow();
        var user2 = accountHolderRepository.findById(studentCheckingDTO.getSecondaryOwner()).orElseThrow();

        var byInstace = StudentChecking.byDTO(studentCheckingDTO, user1, user2);

        studentCheckingRepository.save(byInstace);
        return StudentCheckingDTO.byObject(byInstace);
    }

}
