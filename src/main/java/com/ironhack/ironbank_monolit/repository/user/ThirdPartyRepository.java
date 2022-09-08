package com.ironhack.ironbank_monolit.repository.user;

import com.ironhack.ironbank_monolit.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Long> {

}
