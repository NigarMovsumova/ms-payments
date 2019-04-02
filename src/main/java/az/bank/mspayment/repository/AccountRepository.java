package az.bank.mspayment.repository;

import az.bank.mspayment.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity getByAccountId(String toppedUpAccountId);
}
