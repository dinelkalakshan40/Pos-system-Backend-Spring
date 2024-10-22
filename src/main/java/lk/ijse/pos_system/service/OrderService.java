package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.OrderDTO;
import lk.ijse.pos_system.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    String generateNewOrderId();
    OrderEntity saveOrder(OrderDTO orderDTO);

}
