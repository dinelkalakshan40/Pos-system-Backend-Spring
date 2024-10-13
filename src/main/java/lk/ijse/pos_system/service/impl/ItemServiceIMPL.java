package lk.ijse.pos_system.service.impl;

import lk.ijse.pos_system.dao.ItemDao;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.entity.ItemEntity;
import lk.ijse.pos_system.service.ItemService;
import lk.ijse.pos_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
