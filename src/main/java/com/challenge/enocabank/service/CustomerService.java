package com.challenge.enocabank.service;

import com.challenge.enocabank.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO);
    List<CustomerDTO> findAll();
    CustomerDTO findOneCustomerById(Long id);
    void deleteCustomerById(Long id);
}
