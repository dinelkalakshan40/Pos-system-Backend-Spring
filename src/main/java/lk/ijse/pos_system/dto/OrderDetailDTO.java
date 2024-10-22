package lk.ijse.pos_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements Serializable {
    private Long id;
    private ItemDTO item;
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String orderQTY;
    private String total;
    private OrderDTO order;

}
