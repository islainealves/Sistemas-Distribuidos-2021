package a5;

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
               int idade=Integer.parseInt(mensagem);
               String categoria="";
               if(idade>=5 && idade<=7) {
            	   categoria = "infantil A";
               }else if(idade>=8 && idade<=10) {
            	   categoria = "infantil B";
               }else if(idade>=11 && idade<=13) {
            	   categoria = "juvenil A";
               }else if(idade>=14 && idade<=17) {
            	   categoria = "juvenil B";
               }else if(idade>=18) {
            	   categoria = "adulto";
               }
               //System.out.println(mensagem);
               outToClient.writeBytes(categoria+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}