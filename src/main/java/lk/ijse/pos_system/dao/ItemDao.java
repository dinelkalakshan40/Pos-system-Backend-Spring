package lk.ijse.pos_system.dao;

import lk.ijse.pos_system.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemEntity, String> {
}
