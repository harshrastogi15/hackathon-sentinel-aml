package com.harsh.azentio.customer.service;

import com.harsh.azentio.customer.dto.request.CreateCustomerRequest;
import com.harsh.azentio.customer.dto.response.CustomerResponse;
import com.harsh.azentio.customer.entity.Customer;
import com.harsh.azentio.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        if (customerRepository.existsByCustomerRef(request.customerRef())) {
            throw new IllegalArgumentException(
                    "Customer reference already exists"
            );
        }

        if (customerRepository.existsByIdNumber(request.idNumber())) {
            throw new IllegalArgumentException(
                    "Customer ID number already exists"
            );
        }

        Customer customer = Customer.builder()
                .customerRef(request.customerRef())
                .name(request.name())
                .idNumber(request.idNumber())
                .riskRating(request.riskRating())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return toResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found: " + id
                        )
                );

        return toResponse(customer);
    }

    private CustomerResponse toResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerRef(),
                customer.getName(),
                customer.getIdNumber(),
                customer.getRiskRating(),
                customer.getCreatedAt()
        );
    }
}