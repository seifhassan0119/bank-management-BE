package intern.evision.bankmanagement.controller;
import intern.evision.bankmanagement.DTO.TransactionDTO;
import intern.evision.bankmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(
            @RequestBody TransactionDTO dto) {

        return ResponseEntity.ok(
                transactionService.deposit(dto)
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(
            @RequestBody TransactionDTO dto) {

        return ResponseEntity.ok(
                transactionService.withdraw(dto)
        );
    }


    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(
            @PathVariable String accountId) {

        return ResponseEntity.ok(
                transactionService.getTransactionHistory(accountId)
        );
    }
}