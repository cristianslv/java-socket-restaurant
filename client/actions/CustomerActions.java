package client.actions;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import client.services.AskOrderService;
import client.services.ListMealsService;

public class CustomerActions {
  public CustomerActions(
    Socket clientSocket, 
    DataInputStream inStream, 
    DataOutputStream outStream
  ) {    
    try {
      String customerMessage;
      Console customerMessageReceiver = System.console();
      List<String> allowedOptions = Arrays.asList("listar", "pedir");      
          
      outStream.writeUTF("customer");
      outStream.flush();

      String id = inStream.readUTF();
      System.out.println("\n\n");

      do {
        System.out.println("__________________\n\n");
        System.out.println("Bem vindo Cliente!\n");
        System.out.println("__________________\n");
        System.out.println("Ações diponíveis:");
        System.out.println("Listar itens disponíveis no cardápio (listar)");
        System.out.println("Pedir um item do cardápio (pedir)\n");
        
        customerMessage =  customerMessageReceiver.readLine("Digite a sentença de forma correta aqui: ");
        
        if (!allowedOptions.contains(customerMessage)) {
          continue;
        }

        switch (customerMessage) {
          case "listar":
            ListMealsService.execute(inStream, outStream);
          break;
          case "pedir":
            AskOrderService.execute(inStream, outStream, id);
          break;
          default:
          break;
        }
      } while(!customerMessage.equals("exit"));
      
      inStream.close();
      outStream.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
