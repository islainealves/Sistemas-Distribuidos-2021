package a4;

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
               double altura=0, pesoideal=0;
               altura=Double.parseDouble(array[0]);
               if(array[1].equals("feminino")) {
            	   pesoideal = (62.1 * altura) - 44.7;
               } else if(array[1].equals("masculino")) {
            	   pesoideal = (72.7 * altura) - 58;
               }
               //System.out.println(mensagem);
               outToClient.writeBytes("Peso ideal: "+pesoideal+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}