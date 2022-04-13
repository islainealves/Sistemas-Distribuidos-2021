package a3;

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
               
               double n1=Double.parseDouble(array[0]);
               double n2=Double.parseDouble(array[1]);
               double n3=Double.parseDouble(array[2]);
               double media=(n1+n2)/2;
               if(media>=7) {
            	   mensagem="Aprovado";
               }else if(media>=3) {
            	   media=(media+n3)/2;
            	   if(media>=5) {
            		   mensagem="Aprovado";
            	   }else {
            		   mensagem="Reprovado";
            	   }
               }else {
            	   mensagem="Reprovado";
               }
               //System.out.println(mensagem);
               outToClient.writeBytes(mensagem+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}