package multiserver.repositories;

import java.io.FileWriter;
import java.io.IOException;

import multiserver.entities.Client;

public class ClientRepository {

  public void save(Client client) {
    try {
      FileWriter clientsDB = new FileWriter("clients.csv", true);

      clientsDB.append(String.join(",", client.getId(), client.getType()));
      clientsDB.append("\n");

      clientsDB.flush();
      clientsDB.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
