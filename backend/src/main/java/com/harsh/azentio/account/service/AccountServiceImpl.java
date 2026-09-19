package com.harsh.azentio.account.service;

import com.harsh.azentio.account.dto.request.CreateAccountRequest;
import com.harsh.azentio.account.entity.Account;
import com.harsh.azentio.account.repository.AccountRepository;
import com.harsh.azentio.customer.entity.Customer;
import com.harsh.azentio.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Account createAccount(CreateAccountRequest request) {

        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw new IllegalArgumentException("Account already exists");
        }

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found"));

        Account account = Account.builder()
                .accountNumber(request.accountNumber())
                .customer(customer)
                .type(request.type())
                .currency(request.currency().toUpperCase())
                .openingDate(request.openingDate())
                .riskRating(request.riskRating())
                .build();

        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found"));
    }
}