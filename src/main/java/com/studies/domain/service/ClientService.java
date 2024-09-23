package com.studies.domain.service;

import com.studies.domain.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ClientService {
    private final EntityManager entityManager;

    public ClientService(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Client findById(Integer id){
        return entityManager.find(Client.class, id);
    }

    public Client save(Client  client){
        entityManager.getTransaction().begin();
        client = entityManager.merge(client);
        entityManager.getTransaction().commit();
        return client;
    }

    public void remove(Client client){
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
    }
}
