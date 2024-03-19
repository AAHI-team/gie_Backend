package org.example;

import org.example.model.Client;
import org.example.repository.HibernateClientRepository;


public class Main {
    public static void main(String[] args) {
        // Instantiate ClientService

HibernateClientRepository repo = new HibernateClientRepository();
        // Example usage
        Client companyClient1 = new Client("Google", "google@gmail.com", "+0012569847", "07 Silicon Valley ", "company");
        Client companyClient = new Client("meta", "meta@gmail.com", "+09921365478", "St 55  Silicon Valley", "company");

        repo.save(companyClient1);
        repo.save(companyClient);



    }
}
