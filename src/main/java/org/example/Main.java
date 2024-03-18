package org.example;

import static spark.Spark.*;
import org.example.model.Client;
import org.example.repository.ClientRepo;
import org.example.repository.HibernateClientRepository;
import org.example.service.ClientService;
import org.example.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        // Instantiate ClientService

HibernateClientRepository repo = new HibernateClientRepository();
        // Example usage
        Client client = new Client("Mohamed Mah", "mohamedMah@gmail.com", "0618409719", "14 lkhamra Rabat");
        Client client2 = new Client("badr tahiri", "badrTahiri@gmail.com", "0665478522", "14 ya39ob Rabat");
        repo.save(client);
        repo.save(client2);



    }
}
