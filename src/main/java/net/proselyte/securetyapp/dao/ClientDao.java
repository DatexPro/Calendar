package net.proselyte.securetyapp.dao;

import net.proselyte.securetyapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientDao extends JpaRepository<Client, Long> {
    @Query("select c from Client c where c.name=:name")
    Client findByName(@Param("name") String name);
}
