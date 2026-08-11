package intern.evision.bankmanagement.repos;

import intern.evision.bankmanagement.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel,Integer>{

}