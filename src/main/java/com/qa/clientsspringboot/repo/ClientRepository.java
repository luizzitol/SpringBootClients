package com.qa.clientsspringboot.repo;

import com.qa.clientsspringboot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {


}
