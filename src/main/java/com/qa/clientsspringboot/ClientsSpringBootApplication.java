package com.qa.clientsspringboot;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.repo.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class ClientsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsSpringBootApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunnerStudentConfig(ClientRepository clientRepository) {
        return args -> {

			Client elon = new Client("elon", "ElonMusk@mail.con", LocalDate.of(1969, Month.APRIL, 20));
			Client luis = new Client("luis", "luis@gmail.com", LocalDate.of(1990, Month.JANUARY, 13));
			Client alex = new Client("alex", "alex@gmail.com", LocalDate.of(2000, Month.JULY, 23));

            clientRepository.saveAll(List.of(elon, luis, alex));
        };
    }
}
