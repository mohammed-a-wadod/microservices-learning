package com.mohamedcode.customer.services;

import com.mohamedcode.customer.models.entities.Customer;
import com.mohamedcode.customer.models.pojo.CustomerRegistrationRequest;
import com.mohamedcode.customer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        log.info("New customer data: {}", customer);
        //TODO validate the email
        customerRepository.save(customer);
    }
}
