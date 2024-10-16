package lk.ijse.pos_system.service.impl;

import lk.ijse.pos_system.dao.CustomerDao;
import lk.ijse.pos_system.dao.OrderDao;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.service.CustomerService;
import lk.ijse.pos_system.service.OrderService;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    public OrderDao orderDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    public CustomerDao customerDao;
    @Autowired
    public Mapping customerMapping;

    @Override
    public String generateNewOrderId() {
        String lastOrderId = getlastOrderId();
        if (lastOrderId != null && lastOrderId.startsWith("OID")) {
            int lastIdNumber = Integer.parseInt(lastOrderId.substring(4));
            lastIdNumber++;
            String newId = String.format("OID-%03d", lastIdNumber);

            return newId;
        } else {
            return "OID-001";
        }
    }

    public String getlastOrderId() {
        return orderDao.findLastOrderId();
    }
}
