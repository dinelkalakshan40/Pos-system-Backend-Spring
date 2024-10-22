package lk.ijse.pos_system.dto;

import lk.ijse.pos_system.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable {

    private String orderId;
    private String date;
    private CustomerDTO customer;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private double netTotal;
    private double discount;
    private double subTotal;
    private List<OrderDetailDTO> orderDetails;
}
