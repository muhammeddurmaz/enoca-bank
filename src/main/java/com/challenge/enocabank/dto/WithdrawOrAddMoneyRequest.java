package com.challenge.enocabank.dto;

public class WithdrawOrAddMoneyRequest {

    private Long accountId;
    private Double amount;

    public Long getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }
}
