package lk.ijse.pos_system.service.impl;

import lk.ijse.pos_system.dao.ItemDao;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.entity.ItemEntity;
import lk.ijse.pos_system.service.ItemService;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
    @Autowired
    public ItemDao itemDao;

    @Autowired
    public Mapping ItemMapping;

    public boolean saveItem(ItemDTO itemDTO){
        try {
            // Convert DTO to Entity and save it using DAO
            ItemEntity savedItem =itemDao.save(ItemMapping.toItemEntity(itemDTO));

            // Return true if the customer was saved, false if not
            return savedItem != null;

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the Item: " + e.getMessage(), e);
        }
    }
    @Override
    public String generateNewItemId() {
        String lastItemId = getLastItemIdFromDatabase(); // Retrieve the last customer ID from the database
        if (lastItemId != null && lastItemId.startsWith("ID")) {
            int lastIdNumber = Integer.parseInt(lastItemId.substring(4)); // Extract the numeric part
            lastIdNumber++; // Increment the numeric part
            String newId = String.format("ID-%03d", lastIdNumber);


            return newId; // Format the new ID as CID-001
        } else {
            return "ID-001"; // Start with CID-001 if no ID exists in the database
        }
    }

    public String getLastItemIdFromDatabase() {
        return itemDao.findLastItemId();
    }
    @Override
    public boolean updateItem(String itemID, ItemDTO itemDTO) {
        Optional<ItemEntity> findItem = itemDao.findById(itemID);
        if (findItem.isPresent()) {
            findItem.get().setItemName(itemDTO.getItemName());
            findItem.get().setItemPrice(itemDTO.getItemPrice());
            findItem.get().setItemQty(itemDTO.getItemQty());
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteItem(String itemID){
        Optional<ItemEntity> foundItem = itemDao.findById(itemID);
        if(foundItem.isPresent()){
            itemDao.delete(foundItem.get());
            return true;

        }
        return false;
    }

}
