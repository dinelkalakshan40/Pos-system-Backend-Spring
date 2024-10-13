package lk.ijse.pos_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {
    @Id
    private String itemID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
}
