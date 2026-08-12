package intern.evision.bankmanagement.service;
import intern.evision.bankmanagement.DTO.TransactionDTO;
import intern.evision.bankmanagement.model.AccountModel;
import intern.evision.bankmanagement.model.TransactionModel;
import intern.evision.bankmanagement.repos.AccountRepository;
import intern.evision.bankmanagement.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    public TransactionDTO deposit(TransactionDTO dto) {

        AccountModel account =
                accountRepository.findById(Integer.parseInt(dto.getAccountId()))
                        .orElseThrow(() -> new RuntimeException("Account not exists"));

        double currentBalance = Double.parseDouble(account.getBalance());
        double amount = Double.parseDouble(dto.getAmount());

        if (amount <= 0)
            throw new RuntimeException("Amount can't be negative");

        double newBalance = currentBalance + amount;

        account.setBalance(String.valueOf(newBalance));
        accountRepository.save(account);

        TransactionModel transaction = toModel(dto);

        transaction.setType("DEPOSIT");
        transaction.setBalanceAfter(String.valueOf(newBalance));

        TransactionModel savedTransaction =
                transactionRepository.save(transaction);

        return toDTO(savedTransaction);
    }

    public TransactionDTO withdraw(TransactionDTO dto) {

        AccountModel account =
                accountRepository.findById(Integer.parseInt(dto.getAccountId()))
                        .orElseThrow(() -> new RuntimeException("Account not exists"));

        double currentBalance = Double.parseDouble(account.getBalance());
        double amount = Double.parseDouble(dto.getAmount());

        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be greater than 0");
        }

        if (amount > currentBalance) {
            throw new RuntimeException("Insufficient balance");
        }

        double newBalance = currentBalance - amount;

        account.setBalance(String.valueOf(newBalance));
        accountRepository.save(account);

        TransactionModel transaction = toModel(dto);

        transaction.setType("WITHDRAWAL");
        transaction.setBalanceAfter(String.valueOf(newBalance));

        TransactionModel savedTransaction =
                transactionRepository.save(transaction);

        return toDTO(savedTransaction);
    }

    public List<TransactionDTO> getTransactionHistory(String accountId) {

        List<TransactionModel> transactions =
                transactionRepository.findByAccountId(accountId);

        return transactions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    private TransactionModel toModel(TransactionDTO dto) {

        TransactionModel model = new TransactionModel();

        model.setId(dto.getId());
        model.setAccountId(dto.getAccountId());
        model.setType(dto.getType());
        model.setAmount(dto.getAmount());
        model.setBalanceAfter(dto.getBalanceAfter());
        model.setDescription(dto.getDescription());
        model.setTimestamp(dto.getTimestamp());

        return model;
    }

    private TransactionDTO toDTO(TransactionModel model) {

        TransactionDTO dto = new TransactionDTO();

        dto.setId(model.getId());
        dto.setAccountId(model.getAccountId());
        dto.setType(model.getType());
        dto.setAmount(model.getAmount());
        dto.setBalanceAfter(model.getBalanceAfter());
        dto.setDescription(model.getDescription());
        dto.setTimestamp(model.getTimestamp());

        return dto;
    }
}
