package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.user.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private String name;
    private String secret;
    private String userName;

    public static AdminDTO byObject(Admin admin){

        return new AdminDTO(admin.getName(), admin.getSecret(), admin.getUserName());
    }

}
