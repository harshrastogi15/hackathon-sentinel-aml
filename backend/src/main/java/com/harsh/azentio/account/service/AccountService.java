package com.harsh.azentio.account.service;

import com.harsh.azentio.account.dto.request.CreateAccountRequest;
import com.harsh.azentio.account.entity.Account;

public interface AccountService {

    Account createAccount(CreateAccountRequest request);

    Account getAccount(Long id);
}