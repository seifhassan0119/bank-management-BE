package intern.evision.bankmanagement.service;

import intern.evision.bankmanagement.DTO.CustomerDTO;
import intern.evision.bankmanagement.model.CustomerModel;
import intern.evision.bankmanagement.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAll(){
        return customerRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());

    }

    public Optional<CustomerDTO> getById(int Id){
        return customerRepository.findById(Id).map(this::mapToDto);
    }

    public CustomerDTO addCustomer(CustomerDTO dto){
        CustomerModel model = mapToEntity(dto);
        CustomerModel saveModel = customerRepository.save(model);
        return mapToDto(saveModel);
    }

    public boolean delete(int id){
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
        return false;
    }

    public CustomerDTO update(CustomerDTO dto, int id){
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerModel updatedModel = mapToEntity(dto);
                    updatedModel.setId(customer.getId());

                    CustomerModel savedCustomer = customerRepository.save(updatedModel);

                    return dto;
                })
                .orElse(null);
    }


    private CustomerDTO mapToDto(CustomerModel customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getDob(),
                customer.getNationalId());
    }

    private CustomerModel mapToEntity(CustomerDTO dto) {
        return new CustomerModel(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNationalId(),
                dto.getDob(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getEmail()
                );
    }
    public Page<CustomerDTO> getAll(int page, int size) {

        Page<CustomerModel> customers =
                customerRepository.findAll(PageRequest.of(page, 10));

        return customers.map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        ));
    }

}
