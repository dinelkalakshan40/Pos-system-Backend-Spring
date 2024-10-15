package lk.ijse.pos_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderdetails")
public class OrderDetailEntity implements Serializable {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "itemID", referencedColumnName = "itemID")
    private ItemEntity item;
    private int orderQty;
    private double unitPrice;
}
