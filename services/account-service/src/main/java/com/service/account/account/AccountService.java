package com.service.account.account;

import com.database.dto.account.AccountRegisterDTO;

public interface AccountService<T> {
    T saveAccountRegister(AccountRegisterDTO accountRegisterDTO) throws Exception;
}