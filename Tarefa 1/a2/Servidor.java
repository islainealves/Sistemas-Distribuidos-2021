package a2;

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
               String array[] = new String[3];
               array = mensagem.split("@");
               
               int idade=Integer.parseInt(array[2]);
               if(array[1].equals("masculino") && idade>=18) {
            	   mensagem="Maior de idade";
               }else if(array[1].equals("feminino") && idade>=21) {
            	   mensagem="Maior de idade";
               }else {
            	   mensagem="Menor de idade";
               }
               //System.out.println(mensagem);
               outToClient.writeBytes(array[0]+": "+mensagem+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}