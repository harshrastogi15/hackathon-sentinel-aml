package com.harsh.azentio.customer.controller;

import com.harsh.azentio.customer.dto.request.CreateCustomerRequest;
import com.harsh.azentio.customer.dto.response.CustomerResponse;
import com.harsh.azentio.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {

        CustomerResponse response =
                customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                customerService.getCustomer(id)
        );
    }
}