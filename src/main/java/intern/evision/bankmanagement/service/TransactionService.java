package intern.evision.bankmanagement.service;

import intern.evision.bankmanagement.DTO.TransactionDTO;
import intern.evision.bankmanagement.model.TransactionModel;
import intern.evision.bankmanagement.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<TransactionDTO> getAll() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TransactionDTO> getById(int id) {
        return transactionRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TransactionDTO addTransaction(TransactionDTO dto) {

        TransactionModel transaction = new TransactionModel();

        transaction.setAccountId(dto.getAccountId());
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setBalanceAfter(dto.getBalanceAfter());
        transaction.setDescription(dto.getDescription());
        transaction.setTimestamp(dto.getTimestamp());

        TransactionModel savedTransaction = transactionRepository.save(transaction);

        return convertToDTO(savedTransaction);
    }

    public TransactionDTO update(TransactionDTO dto, int id) {

        Optional<TransactionModel> existingTransaction =
                transactionRepository.findById(id);

        if (existingTransaction.isEmpty()) {
            return null;
        }

        TransactionModel transaction = existingTransaction.get();

        transaction.setAccountId(dto.getAccountId());
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setBalanceAfter(dto.getBalanceAfter());
        transaction.setDescription(dto.getDescription());
        transaction.setTimestamp(dto.getTimestamp());

        TransactionModel updatedTransaction =
                transactionRepository.save(transaction);

        return convertToDTO(updatedTransaction);
    }

    public boolean delete(int id) {

        if (!transactionRepository.existsById(id)) {
            return false;
        }

        transactionRepository.deleteById(id);

        return true;
    }

    private TransactionDTO convertToDTO(TransactionModel transaction) {

        return new TransactionDTO(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalanceAfter(),
                transaction.getDescription(),
                transaction.getTimestamp()
        );
    }

    public Page<TransactionDTO> getAll(int page, int size) {

        Page<TransactionModel> transactions =
                transactionRepository.findAll(PageRequest.of(page, size));

        return transactions.map(transaction -> new TransactionDTO(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalanceAfter(),
                transaction.getDescription(),
                transaction.getTimestamp()
        ));
    }
}