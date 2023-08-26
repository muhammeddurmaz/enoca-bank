package com.challenge.enocabank.service.impl;

import com.challenge.enocabank.dto.AccountDTO;
import com.challenge.enocabank.dto.WithdrawOrAddMoneyRequest;
import com.challenge.enocabank.errors.AccountNotFoundException;
import com.challenge.enocabank.errors.BadRequestAlertException;
import com.challenge.enocabank.mapper.AccountMapper;
import com.challenge.enocabank.model.Account;
import com.challenge.enocabank.repository.AccountRepository;
import com.challenge.enocabank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CacheManager cacheManager;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, CacheManager cacheManager) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.cacheManager = cacheManager;
    }

    @Override
//    @CachePut(cacheNames = "accounts",key = "#accountDTO.id")
    public AccountDTO createAccount(AccountDTO accountDTO) {
        log.debug("Request to create Account : {}", accountDTO);
        Account account = accountMapper.toEntity(accountDTO);
        account = accountRepository.save(account);
        AccountDTO result = accountMapper.toDto(account);
        addAccountCaches(result);
        return result;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        log.debug("Request to update Account : {}", accountDTO);
        if (!accountRepository.existsById(accountDTO.getId())) {
            throw new AccountNotFoundException("Account not found for update process with id " + accountDTO.getId());
        }
        Account account = accountMapper.toEntity(accountDTO);
        account = accountRepository.save(account);
        clearAccountCaches(accountDTO.getId());
        clearCustomerAccountCaches(accountDTO.getCustomer().getId());
        return accountMapper.toDto(account);
    }

    @Override
    @Cacheable(cacheNames = "customerAccounts",key = "#id")
    public List<AccountDTO> findAllByCustomerId(Long id) {
        log.debug("Request to get all Accounts by Customer id : {}",id);
        return accountRepository.findAllByCustomer_Id(id)
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "accounts",key = "#id")
    public AccountDTO findOneAccountById(Long id) {
        log.debug("Request to get Account : {}", id);
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new AccountNotFoundException("Account could not found by id " + id));
    }

    @Override
    public void deleteAccountById(Long id) {
        log.debug("Request to delete Account : {}", id);
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account not found for delete proccess with id " + id);
        }
        accountRepository.deleteById(id);
        clearAccountCaches(id);
        clearCustomerAccountCaches(accountRepository.findById(id).get().getCustomer().getId());
    }

    @Override
    public AccountDTO withdrawMoney(WithdrawOrAddMoneyRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(request.getAccountId());
        if(optionalAccount.isEmpty()) throw new AccountNotFoundException("Account could not found");

        Account account = optionalAccount.get();
        if(account.getBalance()> request.getAmount()){
            account.setBalance(account.getBalance() - request.getAmount());
            accountRepository.save(account);
        }else {
            throw new BadRequestAlertException("Insufficient funds");
        }
        clearAccountCaches(request.getAccountId());
        clearCustomerAccountCaches(account.getCustomer().getId());
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDTO addMoney(WithdrawOrAddMoneyRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(request.getAccountId());
        if(optionalAccount.isEmpty()) throw new AccountNotFoundException("Account could not found");

        Account account = optionalAccount.get();
        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);
        clearAccountCaches(request.getAccountId());
        clearCustomerAccountCaches(account.getCustomer().getId());
        return accountMapper.toDto(account);
    }

    private void clearAccountCaches(Long id) {
        Objects.requireNonNull(cacheManager.getCache("accounts")).evict(id);
    }
    private void clearCustomerAccountCaches(Long customerId) {
        Objects.requireNonNull(cacheManager.getCache("customerAccounts")).evict(customerId);
    }
    private void addAccountCaches(AccountDTO accountDTO){
        Objects.requireNonNull(cacheManager.getCache("accounts")).put(accountDTO.getId(),accountDTO);
    }
}
