package lk.ijse.pos_system.controller;


import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
