package client.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DeleteMealService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream, String id) 
  {
    System.out.println("____________________________\n\n");
    System.out.println("Remoção de item no cardápio!\n");
    System.out.println("____________________________\n");
    System.out.println("Caso o id exista, uma mensagem de sucesso será informada.\n");
    System.out.println("Caso o id não exista, uma mensagem de erro será informada.\n");

    try {
      outStream.writeUTF("delete/"+id);
      outStream.flush();
      
      System.out.println("\n\n" + inStream.readUTF() + "\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
