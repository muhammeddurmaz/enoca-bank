package com.challenge.enocabank.mapper;

import com.challenge.enocabank.dto.AccountDTO;
import com.challenge.enocabank.dto.CustomerDTO;
import com.challenge.enocabank.model.Account;
import com.challenge.enocabank.model.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {

    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    AccountDTO toDto(Account s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
