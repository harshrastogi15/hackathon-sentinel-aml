package com.harsh.azentio.customer.repository;

import com.harsh.azentio.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerRef(String customerRef);

    boolean existsByCustomerRef(String customerRef);

    boolean existsByIdNumber(String idNumber);
}