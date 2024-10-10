package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dto.CustomerDTO;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO customerDTO);
    String generateNewCustomerId();
    boolean updateCustomer(String id,CustomerDTO customerDTO);

    boolean deleteCustomer(String id);
}
