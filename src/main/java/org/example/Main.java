package org.example;

import com.google.gson.Gson;
import org.example.model.Client;
import org.example.repository.HibernateClientRepository;
import spark.Spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Instantiate HibernateClientRepository
        HibernateClientRepository repo = new HibernateClientRepository();

        // Example data
        Client companyClient1 = new Client("Google", "google@gmail.com", "+0012569847", "07 Silicon Valley ", "company");
        Client companyClient2 = new Client("Meta", "meta@gmail.com", "+09921365478", "St 55  Silicon Valley", "company");

        // Save clients
        repo.save(companyClient1);
        repo.save(companyClient2);

        // Instantiate Gson for JSON serialization
        Gson gson = new Gson();

        // Start Spark server
        Spark.port(4567);
        System.out.println("Server started at http://localhost:4567");

        // Define Spark route to fetch members
        Spark.get("/clients", (req, res) -> {
            List<Map<String, Object>> data = new ArrayList<>();

            // Retrieve clients from the repository
            List<Client> clients = repo.getAllClients();

            // Convert clients to map representation
           /* for (Client client : clients) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("name", client.getName());
                userData.put("email", client.getEmail());
                userData.put("phone", client.getPhone());
                userData.put("address", client.getAddress());
                userData.put("client_type", client.getClientType());
                data.add(userData);
            }
            */

            // Set response type and return data as JSON
            res.type("application/json");
            return clients;
        }, gson::toJson);
    }
}
