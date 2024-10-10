package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO customerDTO);
    String generateNewCustomerId();
    boolean updateCustomer(String id,CustomerDTO customerDTO);

    boolean deleteCustomer(String id);
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(String customerId);
}
