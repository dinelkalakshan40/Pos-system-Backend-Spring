package lk.ijse.pos_system.dao;

import lk.ijse.pos_system.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDao extends JpaRepository<OrderEntity,String> {
    @Query(value = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1", nativeQuery = true)
    String findLastOrderId();
}
