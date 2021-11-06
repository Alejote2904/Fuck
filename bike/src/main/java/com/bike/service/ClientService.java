package com.bike.service;

import com.bike.model.Client;
import com.bike.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (e.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public boolean deleteClient(int id) {

        Optional<Client> client = getClient(id);

        if (client.isEmpty()) {
            return false;
        } else {
            clientRepository.delete(client.get());
            return true;
        }
    }
    
    /*
        {"idClient":1,"name":"Adeodato Sanchez","email":"agustin@gmail.com",
        "password":"adeodato123","age":15}

    */
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> cliente = clientRepository.getClient(client.getIdClient());
            if (!cliente.isEmpty()) {
                if (client.getName() != null) {
                    cliente.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    cliente.get().setEmail(client.getEmail());
                }
                if (client.getPassword()!= null) {
                    cliente.get().setPassword(client.getPassword());
                }
                if (client.getAge()!= null) {
                    cliente.get().setAge(client.getAge());
                }

                return clientRepository.save(cliente.get());
            }
        }
        return client;
    }
}
