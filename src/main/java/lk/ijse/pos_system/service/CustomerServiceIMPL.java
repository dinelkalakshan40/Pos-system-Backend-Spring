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
    public void saveCustomer(CustomerDTO customerDTO) {
        try {
            // Generate a new customer ID

            // Convert DTO to Entity and save it using DAO
            CustomerEntity savedCustomer = customerDao.save(CustomerMapping.toCustomerEntity(customerDTO));

            // Check if the customer was saved successfully
            if (savedCustomer == null) {
                throw new Exception("Failed to save the customer.");
            }

        } catch (Exception e) {
            // Handle the exception or rethrow it as needed
            throw new RuntimeException("Error occurred while saving the customer: " + e.getMessage(), e);
        }

    }
}