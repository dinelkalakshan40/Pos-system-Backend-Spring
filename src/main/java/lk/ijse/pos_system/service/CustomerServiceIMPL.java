package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dao.CustomerDao;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.util.AppUtil;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    public CustomerDao customerDao;
    @Autowired
    public Mapping CustomerMapping;

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {

        try {
            // Convert DTO to Entity and save it using DAO
            CustomerEntity savedCustomer = customerDao.save(CustomerMapping.toCustomerEntity(customerDTO));

            // Return true if the customer was saved, false if not
            return savedCustomer != null;

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the customer: " + e.getMessage(), e);
        }

    }
}