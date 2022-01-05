package com.mohamedcode.customer.services;

import com.mohamedcode.clients.fraud.interfaces.FraudClient;
import com.mohamedcode.clients.fraud.interfaces.NotificationClient;
import com.mohamedcode.clients.fraud.pojos.customer.CustomerRegistrationRequest;
import com.mohamedcode.clients.fraud.pojos.fraud.FraudCheckResponse;
import com.mohamedcode.clients.fraud.pojos.notification.NotificationRequest;
import com.mohamedcode.customer.models.entities.Customer;
import com.mohamedcode.customer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              NotificationClient notificationClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        log.info("New customer data: {}", customer);
        //TODO check if email is valid
        //TODO check if email is not taken
        customerRepository.saveAndFlush(customer);
        //TODO check if customer isFraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) throw new IllegalStateException("fraudster");
        //TODO make it async i.e add to queue
        notificationClient.sendNotification( new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to my code...",
                        customer.getFirstName())
        ));
    }

}
