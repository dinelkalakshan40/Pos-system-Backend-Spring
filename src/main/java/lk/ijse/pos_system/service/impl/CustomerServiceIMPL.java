package lk.ijse.pos_system.service.impl;

import lk.ijse.pos_system.dao.CustomerDao;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.service.CustomerService;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Override
    public boolean deleteCustomer(String id){
        Optional<CustomerEntity> foundCustomer = customerDao.findById(id);
        if(foundCustomer.isPresent()){
            customerDao.delete(foundCustomer.get());
            return true;

        }
        return false;
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return CustomerMapping.asCustomerDTOList( customerDao.findAll());
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        Optional<CustomerEntity> customer = customerDao.findById(id);

        // If the customer is found, return the DTO; otherwise, return null
        return customer.map(this::convertToDTO).orElse(null);
    }

    private CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}