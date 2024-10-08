package lk.ijse.pos_system.controller;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.service.CustomerService;
import lk.ijse.pos_system.service.CustomerServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody CustomerDTO customerDTO){
        try {
            customerService.saveCustomer(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
