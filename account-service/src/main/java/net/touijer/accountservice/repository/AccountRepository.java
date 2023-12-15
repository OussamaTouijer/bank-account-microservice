package net.touijer.accountservice.repository;

import net.touijer.accountservice.entitise.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount,String> {
}
