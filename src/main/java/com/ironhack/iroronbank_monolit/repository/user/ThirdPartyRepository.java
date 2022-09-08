package com.ironhack.iroronbank_monolit.repository.user;

import com.ironhack.iroronbank_monolit.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Long> {

}
