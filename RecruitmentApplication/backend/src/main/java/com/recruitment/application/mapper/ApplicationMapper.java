package com.recruitment.application.mapper;

import com.recruitment.application.dto.ApplicationDTO;
import com.recruitment.application.model.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    Application fromDTO(ApplicationDTO info);

    ApplicationDTO toDTO(Application info);
}
