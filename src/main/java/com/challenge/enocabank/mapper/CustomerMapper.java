package com.challenge.enocabank.mapper;

import com.challenge.enocabank.dto.CustomerDTO;
import com.challenge.enocabank.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer>{
}
