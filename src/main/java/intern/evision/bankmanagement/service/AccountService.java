package intern.evision.bankmanagement.service;

import intern.evision.bankmanagement.DTO.AccountDTO;
import intern.evision.bankmanagement.model.AccountModel;
import intern.evision.bankmanagement.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<AccountDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Optional<AccountDTO> getById(int id) {
        return accountRepository.findById(id)
                .map(this::mapToDto);
    }

    public AccountDTO create(AccountDTO dto) {
        AccountModel account = mapToEntity(dto);
        AccountModel savedAccount = accountRepository.save(account);

        return mapToDto(savedAccount);
    }

    public AccountDTO update(AccountDTO dto, int id) {
        return accountRepository.findById(id)
                .map(account -> {

                    account.setCustomerid(dto.getCustomerId());
                    account.setAccountnumber(dto.getAccountNumber());
                    account.setAccountType(dto.getAccountType());
                    account.setBalance(dto.getBalance());
                    account.setCurrency(dto.getCurrency());
                    account.setStatus(dto.getStatus());

                    AccountModel updatedAccount = accountRepository.save(account);

                    return mapToDto(updatedAccount);
                })
                .orElse(null);
    }

    public boolean delete(int id) {
        Optional<AccountModel> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            return false;
        }

        accountRepository.deleteById(id);
        return true;
    }

    private AccountModel mapToEntity(AccountDTO dto) {
        AccountModel account = new AccountModel();

        account.setCustomerid(dto.getCustomerId());
        account.setAccountnumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setCurrency(dto.getCurrency());
        account.setStatus(dto.getStatus());

        return account;
    }

    private AccountDTO mapToDto(AccountModel account) {
        return new AccountDTO(
                account.getId(),
                account.getCustomerid(),
                account.getAccountnumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus()
        );
    }
    public Page<AccountDTO> getAll(int page, int size) {

        Page<AccountModel> accounts =
                accountRepository.findAll(PageRequest.of(page, 10));

        return accounts.map(account -> new AccountDTO(
                account.getId(),
                account.getCustomerid(),
                account.getAccountnumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus()
        ));
    }
}