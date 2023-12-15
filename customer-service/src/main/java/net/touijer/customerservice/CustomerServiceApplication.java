package net.touijer.customerservice;

import net.touijer.customerservice.config.GlobalConfig;
import net.touijer.customerservice.entities.Customer;
import net.touijer.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)//audemarage va treter la class pour charger la config
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customerList=List.of(
                    Customer.builder()
                            .firstName("oussama")
                            .lastName("touijer")
                            .email("oussama@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("youness")
                            .lastName("touijer")
                            .email("youness@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("wydad")
                            .lastName("ziti")
                            .email("wydad@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("rajae")
                            .lastName("balghiti")
                            .email("rajae@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("hassan")
                            .lastName("moussa")
                            .email("hassan@gmail.com")
                            .build()
            );


            customerRepository.saveAll(customerList);


        };
    }
}
