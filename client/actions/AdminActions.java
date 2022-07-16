package client.actions;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import client.services.CreateMealService;

public class AdminActions {
  public AdminActions(
    Socket clientSocket, 
    DataInputStream inStream, 
    DataOutputStream outStream
  ) {    
    try {
      String adminMessage;
      Console adminMessageReceiver = System.console();
      List<String> allowedOptions = Arrays.asList("listar","criar");      
         
      outStream.writeUTF("admin");
      outStream.flush();

      String id = inStream.readUTF();
      System.out.println("\n\n");

      do {
        System.out.println("________________________\n\n");
        System.out.println("Bem vindo Administrador!\n");
        System.out.println("________________________\n");
        System.out.println("Ações diponíveis:");
        System.out.println("Criar item no cardápio (criar)");
        System.out.println("Listar itens do cardápio (listar)");
        System.out.println("Remover item do cardápio (remover,id)\n");
        
        adminMessage =  adminMessageReceiver.readLine("Digite a sentença de forma correta aqui: ");
        
        if (!allowedOptions.contains(adminMessage) && !adminMessage.contains("remover,")) {
          continue;
        }
  
        switch (adminMessage) {
          case "criar":
            new CreateMealService(inStream, outStream);
          break;
          // case "listar":
          // break;
          // case "remover":
          // break;
          default:
          break;
        }

        outStream.writeUTF(adminMessage);
        outStream.flush();
  
        String serverMessage=inStream.readUTF();
        System.out.println(serverMessage);
      } while(!adminMessage.equals("exit"));
      
      inStream.close();
      outStream.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
