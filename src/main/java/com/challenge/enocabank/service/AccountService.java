package com.challenge.enocabank.service;

import com.challenge.enocabank.dto.AccountDTO;
import com.challenge.enocabank.dto.WithdrawOrAddMoneyRequest;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO);
    List<AccountDTO> findAllByCustomerId(Long id);
    AccountDTO findOneAccountById(Long id);
    void deleteAccountById(Long id);
    AccountDTO withdrawMoney(WithdrawOrAddMoneyRequest request);
    AccountDTO addMoney(WithdrawOrAddMoneyRequest request);
}
