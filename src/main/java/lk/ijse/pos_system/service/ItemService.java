package lk.ijse.pos_system.service;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    boolean saveItem(ItemDTO itemDTO);
    String generateNewItemId();
    boolean updateItem(String itemID, ItemDTO itemDTO);
    boolean deleteItem(String itemID);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(String itemID);
}
