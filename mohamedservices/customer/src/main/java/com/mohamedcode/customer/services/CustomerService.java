package com.mohamedcode.customer.services;

import com.mohamedcode.customer.models.entities.Customer;
import com.mohamedcode.customer.models.pojo.CustomerRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService() {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        //TODO validate the email
        //TODO save customer to DB
        log.info("New customer data: {}", customer);
    }
}
