package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dao.CustomerDao;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.util.AppUtil;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    @Override
    public String generateNewCustomerId() {
        String lastCustomerId = getLastCustomerIdFromDatabase(); // Retrieve the last customer ID from the database
        if (lastCustomerId != null && lastCustomerId.startsWith("CID")) {
            int lastIdNumber = Integer.parseInt(lastCustomerId.substring(4)); // Extract the numeric part
            lastIdNumber++; // Increment the numeric part
            String newId = String.format("CID-%03d", lastIdNumber);


            return newId; // Format the new ID as CID-001
        } else {
            return "CID-001"; // Start with CID-001 if no ID exists in the database
        }
    }

    public String getLastCustomerIdFromDatabase() {
        return customerDao.findLastCustomerId();
    }
    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> findNote = customerDao.findById(id);
        if (findNote.isPresent()) {
            findNote.get().setName(customerDTO.getName());
            findNote.get().setPhone(customerDTO.getPhone());
            findNote.get().setAddress(customerDTO.getAddress());
            return true;
        }
        return false;
    }
}