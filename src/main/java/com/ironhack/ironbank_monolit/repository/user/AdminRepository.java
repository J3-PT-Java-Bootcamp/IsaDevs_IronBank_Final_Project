package com.ironhack.ironbank_monolit.repository.user;

import com.ironhack.ironbank_monolit.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {

    List <Admin> findByName(String name);

    Admin findByUserName(String username);
}
