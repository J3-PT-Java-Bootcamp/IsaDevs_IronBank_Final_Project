package com.ironhack.ironbank_monolit.repository.user;

import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Long> {

    @Query(value = "SELECT B.secret_key, A.name\n" +
            "    FROM user A\n" +
            "    JOIN account B ON B.owner = A.id\n" +
            "    WHERE B.secret_key LIKE :secret AND A.name LIKE :name", nativeQuery = true)
    List<Object[]> findByPasswordAndUser(@Param("secret") String secret, @Param("name") String name);

}
