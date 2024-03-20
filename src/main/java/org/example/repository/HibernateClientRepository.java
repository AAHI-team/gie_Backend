package org.example.repository;

import org.example.model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateClientRepository {
    private EntityManager entityManager;


    public HibernateClientRepository() {
        this.entityManager = HibernateUtility.getEntityManger();
    }

    public void save(Client client) {
       /* try (Session session = entityManager.unwrap(Session.class)) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    public void delete(Client client) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Transaction transaction = session.beginTransaction();
            Client entity = session.find(Client.class, client.getId());
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAll() {
        TypedQuery<Client> query = entityManager.createQuery("from Client", Client.class);
        return query.getResultList();
    }

    public Client findById(int id) {
        return entityManager.find(Client.class, id);
    }

    public Client findByName(String name) {
        TypedQuery<Client> query = entityManager.createQuery("FROM Client WHERE name = :name", Client.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Client> getAllClients() {
        /*try (Session session = em.unwrap(Session.class)) {
            TypedQuery<Client> query = session.createQuery("FROM Client", Client.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the error appropriately in your application
        }
         */
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);
        criteriaQuery.select(clientRoot);
        Query query = entityManager.createQuery(criteriaQuery);
        List<Client> clients = query.getResultList();
        return clients;
    }
}
