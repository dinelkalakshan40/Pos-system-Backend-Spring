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
    private String orderDetailId;
    @ManyToOne
    @JoinColumn(name = "itemID", referencedColumnName = "itemID")
    private ItemEntity item;
    private String itemName;
    private int orderQty;
    private double unitPrice;
    private double total;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private OrderEntity order;

}
