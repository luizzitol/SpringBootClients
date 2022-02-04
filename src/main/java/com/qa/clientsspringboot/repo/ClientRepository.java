package com.qa.clientsspringboot.repo;

import com.qa.clientsspringboot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientsByName (String name);

}
