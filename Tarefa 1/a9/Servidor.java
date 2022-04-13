package a9;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

   public static void main(String[] args) {
  
       try {
           ServerSocket socket = new ServerSocket(40000);
   
           while(true) {    
               Socket connectionSocket = socket.accept();
               BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
               DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               String mensagem = inFromClient.readLine();
               String array[] = new String[2];
               array = mensagem.split("@");
               int valor=Integer.parseInt(array[0]);
               int naipe=Integer.parseInt(array[1]);
               Carta carta = new Carta(valor,naipe);
               
               outToClient.writeBytes(carta.imprimir()+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}