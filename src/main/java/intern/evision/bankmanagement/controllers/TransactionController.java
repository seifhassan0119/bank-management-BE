package intern.evision.bankmanagement.controllers;

import intern.evision.bankmanagement.DTO.TransactionDTO;
import intern.evision.bankmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import intern.evision.bankmanagement.DTO.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getOneTransaction(@PathVariable int id) {

        Optional<TransactionDTO> transactionDTO = transactionService.getById(id);

        if (transactionDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transactionDTO.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(
            @PathVariable int id,
            @RequestBody TransactionDTO dto) {

        TransactionDTO updatedTransaction = transactionService.update(dto, id);

        if (updatedTransaction == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {

        boolean deleted = transactionService.delete(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<TransactionDTO> saveTransaction(
            @RequestBody TransactionDTO transaction) {

        TransactionDTO createdTransaction =
                transactionService.addTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTransaction);
    }
    @GetMapping("/")
    public ResponseEntity<Page<TransactionDTO>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok((Page) transactionService.getAll(page, size));
    }
}