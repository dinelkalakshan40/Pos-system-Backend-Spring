package lk.ijse.pos_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

}
