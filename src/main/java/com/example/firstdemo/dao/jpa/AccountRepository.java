package com.example.firstdemo.dao.jpa;
import com.example.firstdemo.dao.Account;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE username = :username", nativeQuery = true)
    Account findByUsername(@Param("username") String username);
}
