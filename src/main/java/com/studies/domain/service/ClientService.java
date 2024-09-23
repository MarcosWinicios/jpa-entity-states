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
        Client client = entityManager.find(Client.class, 1);
        entityManager.close();
        return client;
    }
}
