package lk.ijse.pos_system.controller;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("orderId")
    public ResponseEntity<Map<String, String>> generateNewOrderId() {
        try {
            String newOrderId = orderService.generateNewOrderId();
            Map<String, String> response = new HashMap<>();
            response.put("orderId", newOrderId);
            response.put("message", "Received Order ID: " + newOrderId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error generating Order ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
