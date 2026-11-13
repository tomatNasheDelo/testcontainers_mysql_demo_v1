package ru.demo.mysql.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.mysql.domain.Customer;
import ru.demo.mysql.domain.Order;
import ru.demo.mysql.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer createCustomerWithOrders(String name, String email, String city, List<Order> orders) {
        Customer customer = new Customer(name, email, city);
        orders.forEach(order -> {
            order.setCustomer(customer);
            customer.getOrders().add(order);
        });
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAllCustomersWithOrders() {
        return customerRepository.findAll();
    }
}
