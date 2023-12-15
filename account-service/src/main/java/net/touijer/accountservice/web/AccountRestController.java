package net.touijer.accountservice.web;

import net.touijer.accountservice.clients.CustomerRestClient;
import net.touijer.accountservice.entitise.BankAccount;
import net.touijer.accountservice.model.Customer;
import net.touijer.accountservice.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class AccountRestController {
    private AccountRepository accountRepository;
    private CustomerRestClient customerRestClient;
// injection des dependence
    public AccountRestController(AccountRepository accountRepository,CustomerRestClient customerRestClient)
    {
        this.accountRepository=accountRepository;
        this.customerRestClient=customerRestClient;


    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> bankAccounts=accountRepository.findAll();
        List<Customer> customers=customerRestClient.allCustomers();
        for(BankAccount b : bankAccounts){
            for(Customer c:customers){
                if(Objects.equals(b.getCustomerId(), c.getId()))
                    b.setCustomer(c);
            }
        }
        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount accountById(@PathVariable String id){
        BankAccount bankAccount=accountRepository.findById(id).get();//bankAccount chercher dans cette base de donnee
        Customer customer= customerRestClient.findCustomerById(bankAccount.getCustomerId());//Customer chercher dans une autre base de donnee (dans la base de donne de microservice CUSTOMER-SERVICE) avec transmit id de constomer exist dans bankAcount
        if(Objects.equals(bankAccount.getCustomerId(), customer.getId()))
            bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
