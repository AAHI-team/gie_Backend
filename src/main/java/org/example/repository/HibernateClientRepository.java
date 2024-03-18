package org.example.repository;

import org.example.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

public class HibernateClientRepository {
    private EntityManager em;
    private EntityTransaction tr;


    public HibernateClientRepository() {


        this.em=HibernateUtility.getEntityManger();
        tr=em.getTransaction();
    }

    public void save(Client cli) {
        // TODO Auto-generated method stub
        try {
            tr.begin();
            em.persist(cli);
            tr.commit();

        }
        catch(Exception e) {
            tr.rollback();
            System.out.println(e);

        }
    }

    public void delete(Client cli) {
        // TODO Auto-generated method stub
        try {
            tr.begin();
            Client entity = em.find(Client.class,cli);
            if (entity != null) { // vérifier que l'objet existe
                em.remove(entity);
            }
            tr.commit();

        }
        catch(Exception e) {
            tr.rollback();
            System.out.println(e);
        }

    }

    public List<Client> getAll() {
        // TODO Auto-generated method stub
        Query query= em.createQuery("from Client");
        return query.getResultList();
    }

    public Client findById(int id) {
        // TODO Auto-generated method stub
        return em.find(Client.class, id);
    }

    public Client findByName(String nom) {
        TypedQuery<Client> query = em.createQuery("FROM Client WHERE client_nom = :nom", Client.class);
        query.setParameter("nom", nom);
        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException e) {
            return null;
        }
    }
}
