package net.proselyte.securetyapp.service;

import net.proselyte.securetyapp.model.Client;

import java.util.List;

public interface ClientService {

    void save(Client client);

    Client findByName(String name);

    List<Client> getAllClient();
}
