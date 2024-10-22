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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemPrice;
    private String orderQTY;
    private String total;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private OrderEntity order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID",referencedColumnName = "itemID")
    private ItemEntity item;

}
