package lk.ijse.pos_system.dao;

import lk.ijse.pos_system.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,String> {
    @Query(value = "SELECT id FROM customers ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastCustomerId();
}
