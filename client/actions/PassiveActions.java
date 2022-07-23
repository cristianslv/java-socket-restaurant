package client.actions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import client.services.ListOrdersService;

public class PassiveActions {
  public PassiveActions(
    Socket clientSocket, 
    DataInputStream inStream, 
    DataOutputStream outStream
  ) {    
    try {
      outStream.writeUTF("passive");
      outStream.flush();

      String id = inStream.readUTF();
      System.out.println("\n\n");

      new Timer().scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run(){
          System.out.println("__________________\n\n");
          System.out.println("Bem vindo Passivo!\n");
          System.out.println("__________________\n");
          System.out.println("Sua função é auxiliar na visualização de pedidos não finalizados!");
          System.out.println("Portanto, a cada 10 segundos, tais pedidos serão listados aqui.\n");

          ListOrdersService.execute(inStream, outStream);
        }
      },0,10000);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
