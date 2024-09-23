package com.studies;

import com.studies.domain.model.Client;
import com.studies.domain.service.ClientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clients-PU");
        ClientService clientService =  new ClientService(entityManagerFactory);
        Client c1 = clientService.findById(1);
        System.out.println(c1);

        entityManagerFactory.close();
    }
}