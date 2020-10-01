package my.spring.exhibitions.repository;

import my.spring.exhibitions.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
