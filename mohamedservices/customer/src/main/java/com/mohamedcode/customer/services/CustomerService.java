package com.mohamedcode.customer.services;

import com.mohamedcode.clients.fraud.FraudClient;
import com.mohamedcode.clients.fraud.pojos.fraud.FraudCheckResponse;
import com.mohamedcode.customer.models.entities.Customer;
import com.mohamedcode.clients.fraud.pojos.customer.CustomerRegistrationRequest;
import com.mohamedcode.customer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        log.info("New customer data: {}", customer);
        //TODO check if email is valid
        //TODO check if email is not taken
        customerRepository.saveAndFlush(customer);
        //TODO check if customer isFraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) throw new IllegalStateException("fraudster");
        //TODO send notification
    }

}
