package com.example.firstdemo.service.helper;

import com.example.firstdemo.Exception.BusinessException;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.JPA.AccountRepository;
import org.springframework.util.StringUtils;

public class AccountValidationHelper {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])[a-zA-Z0-9]+$";

    public static String  validateAccount(AccountDTO accountDTO, AccountRepository accountRepository) {
        if (isInvalidAccount(accountDTO)) {
            return "error.register.empty";
        }

        if (isDuplicateAccount(accountDTO, accountRepository)) {
            return "error.register.DuplicateAccount";
        }

        if (!isValidUsernameFormat(accountDTO.getUsername())) {
            return "error.register.AccountCase";
        }

        if (!isValidPasswordFormat(accountDTO.getPassword())) {
            return "error.register.PasswordCase";
        }
        return  null;
    }

    public static AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        return accountDTO;
    }

    private static boolean isInvalidAccount(AccountDTO accountDTO) {
        return StringUtils.isEmpty(accountDTO.getUsername()) || StringUtils.isEmpty(accountDTO.getPassword());
    }

    private static boolean isValidUsernameFormat(String username) {
        return username.matches(USERNAME_PATTERN);
    }

    private static boolean isValidPasswordFormat(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    private static boolean isDuplicateAccount(AccountDTO accountDTO, AccountRepository accountRepository) {
        Account existingAccount = accountRepository.findByUsername(accountDTO.getUsername());
        return existingAccount != null;
    }
}
