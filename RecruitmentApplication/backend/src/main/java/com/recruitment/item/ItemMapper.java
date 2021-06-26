package com.recruitment.item;

import com.recruitment.item.model.Item;
import com.recruitment.item.model.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    @Mappings({
            @Mapping(target = "reviews", ignore = true)
    })
    Item fromDto(ItemDTO item);

}
