package com.studies;

import com.studies.domain.model.Client;
import com.studies.domain.service.ClientService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clients-PU");
        ClientService clientService =  new ClientService(entityManagerFactory);
        //Consultar um cliente
        Client c1 = clientService.findById(1);
        System.out.println(c1);

        //Criar novo cliente
        Client c2 = clientService.save(new Client("Armazen Feliz"));

        //Atualizar cliente
        c2.setName(c2.getName() + " atualizado");
        clientService.save(c2);

        //Remover um cliente
        Client c3 = clientService.save(new Client("Cliente a ser excluído"));
        System.out.println(c3.toString());
        clientService.remove(c3);



        clientService.close();
        entityManagerFactory.close();
    }
}