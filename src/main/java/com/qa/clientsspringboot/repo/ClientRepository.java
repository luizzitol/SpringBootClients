package com.qa.clientsspringboot.repo;

import com.qa.clientsspringboot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientsByName (String name);

}
