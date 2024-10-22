package lk.ijse.pos_system.dao;

import lk.ijse.pos_system.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetailEntity,Long> {

}
