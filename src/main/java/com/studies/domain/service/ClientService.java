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
     * O merge recebe a entidade retorna uma cópia dessa entidade gerenciada. A entidade enviada continua não sendo gerenciada
     * Qualquer alteração em uma entidade gerenciada dentro do begin e commit, será registrada no DB, pois agora há uma sicronização entre a entidadede e o DB
     * Para impedir esta sincronização é precisa utilizar o método detach
     */

    public Client save(Client  client){
        entityManager.getTransaction().begin();
//        entityManager.persist(client);
        client  = entityManager.merge(client);
        entityManager.detach(client);
        client.setName("Alterando entidade gerenciada");
        entityManager.getTransaction().commit();
        return client;
    }

    /**
     * O método remove não consegue excluir uma entidade não gerenciada. Por isso obter uma entidade gerenciada a partir de uma consulta é uma solução
     *
     */
    public void remove(Client client){
        client = this.findById(client.getId());
        if(client == null) {
            throw new RuntimeException("Cliente não encontrado");
        }
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
    }
}
