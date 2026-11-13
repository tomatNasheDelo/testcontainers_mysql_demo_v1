package ru.demo.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.demo.mysql.domain.Customer;
import ru.demo.mysql.domain.Order;
import ru.demo.mysql.repository.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryIntegrationTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36")
            .withDatabaseName("demo_test")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldPersistCustomerWithOrders() {
        Customer customer = new Customer("Bob Smith", "bob@example.com", "Paris");
        customer.getOrders().add(new Order("Keyboard", 99.99, customer));
        customer.getOrders().add(new Order("Mouse", 24.90, customer));

        Customer saved = customerRepository.saveAndFlush(customer);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOrders()).hasSize(2);
        assertThat(saved.getOrders()).extracting(Order::getProduct)
                .containsExactlyInAnyOrder("Keyboard", "Mouse");
    }
}
