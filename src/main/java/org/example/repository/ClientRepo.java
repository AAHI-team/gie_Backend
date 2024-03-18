package org.example.repository;

import org.example.model.Client;

import java.util.List;

public interface ClientRepo {
    Client findById(int id);

    List<Client> findAll();

    void save(Client client);

    void update(Client client);

    void delete(Client client);
}
