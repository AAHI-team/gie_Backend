package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Client;
import org.example.repository.ClientRepo;
import org.example.exception.ResourceNotFoundException;
import spark.Request;
import spark.Response;

import java.util.List;

public class ClientController {

    private final ClientRepo clientRepo;
    private final Gson gson;

    public ClientController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
        this.gson = new Gson();
    }

    public List<Client> getAllClients(Request request, Response response) {
        response.type("application/json");
        return clientRepo.findAll();
    }

    public Client getClientById(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Client client = clientRepo.findById(id);
        if (client == null) {
            throw new ResourceNotFoundException("Client with ID " + id + " not found");
        }
        response.type("application/json");
        return client;
    }

    public String addClient(Request request, Response response) {
        Client newClient = gson.fromJson(request.body(), Client.class);
        clientRepo.save(newClient);
        response.status(201); // Created
        response.type("application/json");
        return gson.toJson(newClient);
    }

    public String updateClient(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Client updatedClient = gson.fromJson(request.body(), Client.class);
        updatedClient.setId(id); // Ensure ID consistency
        Client existingClient = clientRepo.findById(id);
        if (existingClient == null) {
            throw new ResourceNotFoundException("Client with ID " + id + " not found");
        }
        clientRepo.update(updatedClient);
        response.type("application/json");
        return gson.toJson(updatedClient);
    }

    public String deleteClient(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Client clientToDelete = clientRepo.findById(id);
        if (clientToDelete == null) {
            throw new ResourceNotFoundException("Client with ID " + id + " not found");
        }
        clientRepo.delete(clientToDelete);
        response.status(204); // No content
        return "";
    }
}
