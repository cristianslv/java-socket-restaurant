package client.services;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ListMealsService {
  public ListMealsService(DataInputStream inStream, DataOutputStream outStream) 
  {
    System.out.println("___________________________\n\n");
    System.out.println("Todos os items do cardápio!\n");
    System.out.println("___________________________\n");
    
    try {
      outStream.writeUTF("list/");
      outStream.flush();

      while(inStream.readUTF().equals("null")) {
        System.out.println(inStream.readUTF() + "\n");
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
