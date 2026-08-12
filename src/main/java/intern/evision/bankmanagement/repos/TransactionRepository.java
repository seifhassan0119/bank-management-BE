package intern.evision.bankmanagement.repos;

import intern.evision.bankmanagement.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionModel,Integer>{

    List<TransactionModel> findByAccountId(String accountId);
}