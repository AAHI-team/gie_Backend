package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create a StandardServiceRegistry
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // Configure from hibernate.cfg.xml by default
                    .build();

            // Create MetadataSources
            MetadataSources metadataSources = new MetadataSources(registry);

            // Add entity classes
            metadataSources.addAnnotatedClass(org.example.model.Client.class);
            // Add more entity classes if any

            // Build SessionFactory
            return metadataSources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
