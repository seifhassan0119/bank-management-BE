package intern.evision.bankmanagement.repos;

import intern.evision.bankmanagement.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
}