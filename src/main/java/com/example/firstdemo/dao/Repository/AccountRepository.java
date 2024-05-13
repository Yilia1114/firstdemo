package com.example.firstdemo.dao.Repository;
import com.example.firstdemo.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser(String user);
    Account findById(int id);
}
