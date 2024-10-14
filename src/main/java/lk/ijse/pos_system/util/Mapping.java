package lk.ijse.pos_system.util;

import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.entity.CustomerEntity;
import lk.ijse.pos_system.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //CustomerMapping
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
    //ItemMapping
    public ItemEntity toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,ItemEntity.class);
    }
    public List<ItemDTO> asItemDTOList(List<ItemEntity> itemEntities){
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDTO>>() {}.getType());
    }

}
