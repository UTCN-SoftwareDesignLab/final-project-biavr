package com.recruitment.company.mapper;

import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.company.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company fromDTO(CompanyDTO company);
    CompanyDTO toDTO(Company company);
}
