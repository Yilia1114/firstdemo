package com.example.firstdemo.service.helper;

import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.jpa.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;


public class AccountValidationHelper {

    private AccountValidationHelper() {
        throw new IllegalStateException("Utility class");
    }
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])[a-zA-Z0-9]+$";
    private static final Logger logger = LoggerFactory.getLogger(AccountValidationHelper.class);

    public static String  validateAccount(AccountDTO accountDTO, AccountRepository accountRepository) {
        logger.info("開始驗證帳號密碼");

        if (isInvalidAccount(accountDTO)) {
            logger.error("欲建立的帳號{}或密碼{}為空",accountDTO.getUsername(),accountDTO.getPassword());
            return "error.register.empty";
        }

        if (isDuplicateAccount(accountDTO, accountRepository)) {
            logger.error("欲建立的帳號{}已存在",accountDTO.getUsername());
            return "error.register.duplicate";
        }

        if (!isValidUsernameFormat(accountDTO.getUsername())) {
            logger.error("欲建立的帳號{}不符合建立原則",accountDTO.getUsername());
            return "error.register.account.case";
        }

        if (!isValidPasswordFormat(accountDTO.getPassword())) {
            logger.error("欲建立的密碼{}不符合建立原則",accountDTO.getPassword());
            return "error.register.password.case";
        }
        logger.info("驗證帳號密碼:成功");
        return  null;
    }

    public static AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        return accountDTO;
    }

    private static boolean isInvalidAccount(AccountDTO accountDTO) {
        return ObjectUtils.isEmpty(accountDTO.getUsername()) || ObjectUtils.isEmpty(accountDTO.getPassword());
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
