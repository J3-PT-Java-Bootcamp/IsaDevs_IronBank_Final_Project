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

    public static AdminDTO byObject(Admin admin){
        var dtoAdmin = new AdminDTO();
        dtoAdmin.setName(admin.getName());

        return dtoAdmin;
    }

}
