package ru.demo.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.demo.mysql.domain.Customer;
import ru.demo.mysql.repository.CustomerRepository;

@Configuration
public class AppBootstrap {
    private static final Logger log = LoggerFactory.getLogger(AppBootstrap.class);

    @Bean
    CommandLineRunner seedData(CustomerRepository customerRepository) {
        return args -> {
            long count = customerRepository.count();
            if (count == 0) {
                Customer customer = new Customer();
                customer.setName("Alice Johnson");
                customer.setEmail("alice@example.com");
                customer.setCity("Berlin");
                customer.getOrders().add(new ru.demo.mysql.domain.Order("Laptop", 1200.0, customer));
                customer.getOrders().add(new ru.demo.mysql.domain.Order("Monitor", 350.0, customer));
                customerRepository.save(customer);
                log.info("Bootstrapped demo data into MySQL app database");
            } else {
                log.info("App database already contains {} customers", count);
            }
        };
    }
}
