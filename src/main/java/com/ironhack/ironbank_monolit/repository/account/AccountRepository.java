package com.ironhack.ironbank_monolit.repository.account;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {


    Account findByPrimaryOwner(User user);

    @Query(value = "SELECT *\n" +
                        "FROM account A\n" +
                        "JOIN user U on U.id = A.owner\n" +
                        "WHERE A.id = :id AND U.name LIKE :name", nativeQuery = true)
    Account findByIdAndName(@Param("id") long id, @Param("name") String name);
}
