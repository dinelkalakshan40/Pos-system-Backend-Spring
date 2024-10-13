package lk.ijse.pos_system.controller;


import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/items")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody ItemDTO itemDTO){
        try {
            // Convert DTO to Entity and save it using service
            boolean isSaved = itemService.saveItem(itemDTO);


            if (isSaved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Item saved");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item not saved");
            }

        } catch (Exception e) {
            // Handle any exception and return a 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
    @GetMapping("itemId")
    public ResponseEntity<Map<String, String>> generateItemId() {
        try {
            String newItemId = itemService.generateNewItemId();

            // Create a JSON object to return
            Map<String, String> response = new HashMap<>();
            response.put("itemId", newItemId);
            response.put("message", "Received Item ID: " + newItemId);

            return ResponseEntity.ok(response); // Return 200 OK with the new customer ID as JSON
        } catch (Exception e) {
            // Return 500 Internal Server Error if any exception occurs
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error generating Item ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PutMapping("/{itemID}")
    public ResponseEntity<String> updateItem(@PathVariable("itemID") String itemID, @RequestBody ItemDTO itemDTO) {
        try {
            boolean isUpdated = itemService.updateItem(itemID, itemDTO);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Item Updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not Updated");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer");
        }
    }
    @DeleteMapping("/{itemID}")
    public ResponseEntity<String> delete(@PathVariable("itemID") String itemID) {
        try {
            boolean isUpdated = itemService.deleteItem(itemID);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Item Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not Deleted");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error:");
        }
    }

}
