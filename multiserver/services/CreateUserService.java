package multiserver.services;

import multiserver.entities.Client;
import multiserver.repositories.ClientRepository;

public class CreateUserService {
  public static void execute(int id, String type) {
    ClientRepository clientRepository = new ClientRepository();
    
    Client client = new Client(String.valueOf(id), type);
    
    clientRepository.save(client);
  }
}
