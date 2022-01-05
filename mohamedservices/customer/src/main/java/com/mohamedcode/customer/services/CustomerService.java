package com.mohamedcode.customer.services;

import com.mohamedcode.customer.models.entities.Customer;
import com.mohamedcode.customer.models.pojo.CustomerRegistrationRequest;
import com.mohamedcode.customer.models.pojo.FraudCheckResponse;
import com.mohamedcode.customer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        log.info("New customer data: {}", customer);
        //TODO check if email is valid
        //TODO check if email is not taken
        customerRepository.saveAndFlush(customer);
        //TODO check if customer isFraudster
        ResponseEntity<FraudCheckResponse> fraudCheckResponse = restTemplate.getForEntity("http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class, customer.getId());
        if (Objects.requireNonNull(fraudCheckResponse.getBody()).isFraudster())
            throw new IllegalStateException("fraudster");
        //TODO send notification
    }

}
