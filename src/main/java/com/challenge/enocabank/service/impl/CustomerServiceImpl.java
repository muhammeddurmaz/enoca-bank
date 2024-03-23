package com.challenge.enocabank.service.impl;

import com.challenge.enocabank.dto.AccountDTO;
import com.challenge.enocabank.dto.CustomerDTO;
import com.challenge.enocabank.errors.CustomerNotFoundException;
import com.challenge.enocabank.mapper.CustomerMapper;
import com.challenge.enocabank.model.Customer;
import com.challenge.enocabank.repository.CustomerRepository;
import com.challenge.enocabank.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CacheManager cacheManager;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CacheManager cacheManager) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.cacheManager = cacheManager;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to create Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO result = customerMapper.toDto(customer);
        addCustomerCaches(result);
        return result;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        log.debug("Request to update Customer : {}", customerDTO);
        if (!customerRepository.existsById(customerDTO.getId())) {
            throw new CustomerNotFoundException("Customer not found for update process with id " + customerDTO.getId());
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        clearCustomerCaches(customerDTO.getId());
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "customers")
    public List<CustomerDTO> findAll() {
        log.debug("Request to get all Customers");
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @CachePut(cacheNames = "customers",key = "#id")
    public CustomerDTO findOneCustomerById(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer could not found by id " + id));
    }

    @Override
    public void deleteCustomerById(Long id) {
        log.debug("Request to delete Customer : {}", id);
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found for delete proccess with id " + id);
        }
        customerRepository.deleteById(id);
        clearCustomerCaches(id);
    }

    private void clearCustomerCaches(Long id) {
        Objects.requireNonNull(cacheManager.getCache("customers")).evict(id);
    }

    private void addCustomerCaches(CustomerDTO customerDTO){
        Objects.requireNonNull(cacheManager.getCache("customers")).put(customerDTO.getId(),customerDTO);
    }
}
