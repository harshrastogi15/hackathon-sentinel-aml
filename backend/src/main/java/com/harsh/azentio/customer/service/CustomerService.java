package com.harsh.azentio.customer.service;

import com.harsh.azentio.customer.dto.request.CreateCustomerRequest;
import com.harsh.azentio.customer.dto.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomer(Long id);
}