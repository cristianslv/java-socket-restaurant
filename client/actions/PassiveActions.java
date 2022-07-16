package client.actions;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
          try {
            System.out.println("__________________\n\n");
            System.out.println("Bem vindo Passivo!\n");
            System.out.println("__________________\n");
            System.out.println("Sua função é auxiliar na visualização de pedidos não finalizados!");
            System.out.println("Portanto, a cada 5 segundos, tais pedidos serão listados aqui.\n");

            outStream.writeUTF("apicall");
            System.out.println(inStream.readUTF());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      },0,5000);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
