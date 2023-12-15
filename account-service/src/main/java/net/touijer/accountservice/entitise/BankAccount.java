package net.touijer.accountservice.entitise;

import jakarta.persistence.*;
import lombok.*;
import net.touijer.accountservice.enums.AccountType;
import net.touijer.accountservice.model.Customer;

import java.time.LocalDate;

@Entity
@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class BankAccount {
    @Id
    private String accountId;
    private Double balance;//soulde
    private LocalDate createAt;
    private String currency;//devise
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerId;
    @Transient
    private Customer customer;

}