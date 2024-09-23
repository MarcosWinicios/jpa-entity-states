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

    /**
     * O persist recebe a nova entidade e a torna gerenciada para que seja inserida na base de dados
     * O merge recebe a entidade e a torna uma cópia dessa entidade gerenciada. Qualquer alteração nessa cópia dentro do begin e commit, será registrada no banco de dados
     * para impedir esta sincronização é precisa utilizar o método detach
     */

    public Client save(Client  client){
        entityManager.getTransaction().begin();
        client = entityManager.merge(client);
        entityManager.detach(client);
        client.setName("Alterando entidade gerenciada");
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
