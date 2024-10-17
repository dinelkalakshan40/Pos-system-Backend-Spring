package lk.ijse.pos_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {
    @Id
    private String orderId;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;
    private String customerName;
    private double subTotal;
    private double discount;
    private double netTotal;
    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;
}
