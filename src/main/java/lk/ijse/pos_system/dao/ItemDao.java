package lk.ijse.pos_system.dao;

import lk.ijse.pos_system.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemDao extends JpaRepository<ItemEntity, String> {
    @Query(value = "SELECT itemID FROM item ORDER BY itemID DESC LIMIT 1", nativeQuery = true)
    String findLastItemId();
}
