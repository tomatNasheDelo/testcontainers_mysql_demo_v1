package ru.demo.mysql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.demo.mysql.domain.Customer;
import ru.demo.mysql.domain.Order;
import ru.demo.mysql.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> listCustomers() {
        return customerService.findAllCustomersWithOrders();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CreateCustomerRequest request) {
        List<Order> orders = request.orders().stream()
                .map(item -> new Order(item.product(), item.total(), null))
                .toList();
        return customerService.createCustomerWithOrders(request.name(), request.email(), request.city(), orders);
    }

    public record CreateCustomerRequest(String name, String email, String city, List<OrderRequest> orders) { }
    public record OrderRequest(String product, Double total) { }
}
