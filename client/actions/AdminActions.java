package client.actions;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import client.services.ConfirmOrderService;
import client.services.CreateMealService;
import client.services.DeleteMealService;
import client.services.ListMealsService;
import client.services.UpdateMealService;

public class AdminActions {
  public AdminActions(
    Socket clientSocket, 
    DataInputStream inStream, 
    DataOutputStream outStream
  ) {    
    try {
      String adminMessage;
      Console adminMessageReceiver = System.console();
      List<String> allowedOptions = Arrays.asList("listar","criar","atualizar","remover,","confirmar,","finalizar,");      
         
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
        System.out.println("Remover item do cardápio (remover,id)");
        System.out.println("Atualizar item do cardápio (atualizar)\n");
        System.out.println("Recusar pedido (recusar,id)");
        System.out.println("Confirmar pedido (confirmar,id)");
        System.out.println("Finalizar pedido (finalizar,id)\n");
        
        adminMessage =  adminMessageReceiver.readLine("Digite a sentença de forma correta aqui: ");
        
        if (!allowedOptions.contains(adminMessage)
        && !adminMessage.contains("remover,")
        && !adminMessage.contains("confirmar,")
        && !adminMessage.contains("finalizar,")
        && !adminMessage.contains("recusar,")) {
          continue;
        }
  
        switch (adminMessage) {
          case "criar":
            CreateMealService.execute(inStream, outStream);
          break;
          case "listar":
            ListMealsService.execute(inStream, outStream);
          break;
          case "atualizar":
            UpdateMealService.execute(inStream, outStream);
          break;
          default:
            if (adminMessage.contains("remover,")) {        
              DeleteMealService.execute(inStream, outStream, adminMessage);
            }
          break;
        }

        if (adminMessage.contains("confirmar,")) {        
          ConfirmOrderService.execute(inStream, outStream, adminMessage);
        }

        if (adminMessage.contains("finalizar,")) {        
          DeleteMealService.execute(inStream, outStream, adminMessage);
        }

        if (adminMessage.contains("recusar,")) {        
          DeleteMealService.execute(inStream, outStream, adminMessage);
        }
      } while(!adminMessage.equals("exit"));
      
      inStream.close();
      outStream.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
