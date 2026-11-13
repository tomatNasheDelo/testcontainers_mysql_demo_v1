package ru.demo.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.mysql.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
