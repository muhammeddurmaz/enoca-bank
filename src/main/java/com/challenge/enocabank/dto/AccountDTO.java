package com.challenge.enocabank.dto;

import java.io.Serializable;
import java.util.Objects;

public class AccountDTO implements Serializable {
    private Long id;
    private Double balance;
    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(balance, that.balance) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, customer);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }
}
