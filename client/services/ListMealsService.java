package client.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ListMealsService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream) 
  {
    System.out.println("___________________________\n\n");
    System.out.println("Todos os items do cardápio!\n");
    System.out.println("___________________________\n");
    
    try {
      String message = "";

      outStream.writeUTF("list/");
      outStream.flush();

      while(true) {
        message = inStream.readUTF();

        if (message.equals("exit")) {
          break;
        }

        System.out.println(message + "\n");
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
