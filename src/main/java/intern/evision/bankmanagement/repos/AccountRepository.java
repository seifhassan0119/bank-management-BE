package intern.evision.bankmanagement.repos;

import intern.evision.bankmanagement.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel,Integer>{

}