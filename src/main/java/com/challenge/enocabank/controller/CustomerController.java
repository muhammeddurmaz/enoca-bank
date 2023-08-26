package com.challenge.enocabank.controller;

import com.challenge.enocabank.dto.CustomerDTO;
import com.challenge.enocabank.dto.ResponseDTO;
import com.challenge.enocabank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final String ENTITY_NAME = "customer";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/customer")
    public ResponseEntity<ResponseDTO<CustomerDTO>> save(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedEntity = customerService.save(customerDTO);
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(savedEntity);
        responseDTO.setMessage("Customer creation successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> updateCustomer(@PathVariable(value = "id") Long id, @RequestBody CustomerDTO customerDTO){
        CustomerDTO updatedEntity = customerService.update(customerDTO);
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(updatedEntity);
        responseDTO.setMessage("Customer update successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/customers")
    public ResponseEntity<ResponseDTO<List<CustomerDTO>>> getAllCustomers(){
        List<CustomerDTO> customerDTOS = customerService.findAll();
        ResponseDTO<List<CustomerDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(customerDTOS);
        responseDTO.setMessage("Successful getting customers",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> getOneCustomerById(@PathVariable Long id){
        CustomerDTO customer = customerService.findOneCustomerById(id);
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(customer);
        responseDTO.setMessage("Get customer successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Delete customer successful",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }
}
