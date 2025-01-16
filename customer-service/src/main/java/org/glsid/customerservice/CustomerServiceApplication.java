package org.glsid.customerservice;

import org.glsid.customerservice.entities.Customer;
import org.glsid.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {
    @Value("${my.custom.property}")
    private String myCustomProperty;

    @Value("${eureka.instance.prefer-ip-address}")
    private String globalProperty;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null, "Hassan", "hassan@gmail.com"));
            customerRepository.save(new Customer(null, "Mohamed", "mohamed@gmail.com"));
            customerRepository.save(new Customer(null, "haitam", "haitam@gmail.com"));
            customerRepository.save(new Customer(null, "lamyae", "lamyae@gmail.com"));
            customerRepository.save(new Customer(null, "soufian", "soufian@gmail.com"));
            System.out.println("***********************");
            System.out.println(myCustomProperty);
            System.out.println(globalProperty);
            System.out.println("***********************");

        };
    }

}
