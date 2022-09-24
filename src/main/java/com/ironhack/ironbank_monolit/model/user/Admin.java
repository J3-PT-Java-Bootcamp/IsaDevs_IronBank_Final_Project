package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.dto.userDTO.AdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Admin extends User{

    public Admin(String name, String secret, String userName) {
        super(name, secret, userName);
    }

    public static Admin fromDTO(AdminDTO adminDTO){
        return new Admin(adminDTO.getName(), adminDTO.getSecret(), adminDTO.getUserName());
    }
}
