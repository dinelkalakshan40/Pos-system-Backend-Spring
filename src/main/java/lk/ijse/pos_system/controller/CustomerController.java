package lk.ijse.pos_system.controller;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
}

