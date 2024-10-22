package lk.ijse.pos_system.controller;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.OrderDTO;
import lk.ijse.pos_system.entity.OrderEntity;
import lk.ijse.pos_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderEntity savedOrder = orderService.saveOrder(orderDTO);
            if (savedOrder != null) {
                return ResponseEntity.ok("Order placed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not saved.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error. Failed to place order.");
        }
    }

}
