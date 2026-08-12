package intern.evision.bankmanagement.controllers;

import intern.evision.bankmanagement.DTO.CustomerDTO;
import intern.evision.bankmanagement.model.CustomerModel;
import intern.evision.bankmanagement.repos.CustomerRepository;
import intern.evision.bankmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getOneCustomer(@PathVariable int id) {

        Optional<CustomerDTO> customerDTO = customerService.getById(id);

        if (customerDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerDTO.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable int id,
            @RequestBody CustomerDTO dto){

        CustomerDTO updatedCustomer = customerService.update(dto, id);

    if (updatedCustomer == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        boolean deleted = customerService.delete(id);

        if (!deleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {

        CustomerDTO createdCustomer = customerService.addCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdCustomer);
    }

}

