package org.example.service;

import org.example.model.Client;
import org.example.repository.ClientRepo;

import java.util.List;

public class ClientService {
    private final ClientRepo clientRepository;

    public ClientService(ClientRepo clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void update(Client client) {
        clientRepository.update(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
