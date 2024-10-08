package lk.ijse.pos_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String address;

}
