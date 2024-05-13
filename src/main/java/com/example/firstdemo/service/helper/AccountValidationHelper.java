package com.example.firstdemo.service.helper;

import com.example.firstdemo.BusinessException;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.Repository.AccountRepository;
import org.springframework.util.StringUtils;

public class AccountValidationHelper {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])[a-zA-Z0-9]+$";

    public static void validateAccount(AccountDTO accountDTO, AccountRepository accountRepository) {
        if (isInvalidAccount(accountDTO)) {

            throw new BusinessException("帳號或密碼不能為空");
        }

        if (isDuplicateAccount(accountDTO, accountRepository)) {
            throw new BusinessException("帳號已存在");
        }

        if (!isValidUsernameFormat(accountDTO.getUser())) {
            throw new BusinessException("帳號必須包含至少一個大寫字母，並且只能是字母和數字的組合");
        }

        if (!isValidPasswordFormat(accountDTO.getPassword())) {
            throw new BusinessException("密碼必須包含至少一個大寫字母，並且只能是字母和數字的組合");
        }
    }

    public static AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUser(account.getUser());
        return accountDTO;
    }

    private static boolean isInvalidAccount(AccountDTO accountDTO) {
        return StringUtils.isEmpty(accountDTO.getUser()) || StringUtils.isEmpty(accountDTO.getPassword());
    }

    private static boolean isValidUsernameFormat(String username) {
        return username.matches(USERNAME_PATTERN);
    }

    private static boolean isValidPasswordFormat(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    private static boolean isDuplicateAccount(AccountDTO accountDTO, AccountRepository accountRepository) {
        Account existingAccount = accountRepository.findByUser(accountDTO.getUser());
        return existingAccount != null;
    }
}
