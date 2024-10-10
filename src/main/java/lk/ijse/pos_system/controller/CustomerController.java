package lk.ijse.pos_system.controller;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody CustomerDTO customerDTO) {
        try {
            // Convert DTO to Entity and save it using service
            boolean isSaved = customerService.saveCustomer(customerDTO);

            if (isSaved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer saved");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not saved");
            }

        } catch (Exception e) {
            // Handle any exception and return a 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
    @GetMapping("customerId")
    public ResponseEntity<Map<String, String>> generateCustomerId(){
        try {
            String newCustomerId = customerService.generateNewCustomerId();

            // Create a JSON object to return
            Map<String, String> response = new HashMap<>();
            response.put("customerId", newCustomerId);
            response.put("message","Received customer ID: "+newCustomerId);

            return ResponseEntity.ok(response); // Return 200 OK with the new customer ID as JSON
        } catch (Exception e) {
            // Return 500 Internal Server Error if any exception occurs
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error generating customer ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable ("id") String id,@RequestBody CustomerDTO customerDTO) {
        try {
            boolean isUpdated = customerService.updateCustomer(id,customerDTO);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer Updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not Updated");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable ("id") String id){
        try {
            boolean isUpdated = customerService.deleteCustomer(id);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not Deleted");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error:");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerById(@PathVariable("id") String id) {
        try {
            // Fetch the customer from the service layer
            String standardizedId = id.toUpperCase();
            CustomerDTO customer = customerService.getCustomerById(standardizedId);

            if (customer != null) {
                // Return customer details with 200 OK status
                return ResponseEntity.ok(customer);
            } else {
                // If customer is not found, return 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not found in the database.");
            }
        } catch (Exception e) {
            // Return 500 Internal Server Error in case of any exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving customer: " + e.getMessage());
        }
    }
}

