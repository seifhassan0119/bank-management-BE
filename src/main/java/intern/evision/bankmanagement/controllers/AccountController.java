package intern.evision.bankmanagement.controllers;

import intern.evision.bankmanagement.DTO.AccountDTO;
import intern.evision.bankmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getOneAccount(@PathVariable int id) {

        Optional<AccountDTO> accountDTO = accountService.getById(id);

        if (accountDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(accountDTO.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable int id,
            @RequestBody AccountDTO dto) {

        AccountDTO updatedAccount = accountService.update(dto, id);

        if (updatedAccount == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {

        boolean deleted = accountService.delete(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO account) {

        AccountDTO createdAccount = accountService.create(account);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdAccount);
    }

    @GetMapping("/")
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(accountService.getAll(page, size));
    }
}