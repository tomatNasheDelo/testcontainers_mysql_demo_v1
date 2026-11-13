package ru.demo.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.mysql.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
