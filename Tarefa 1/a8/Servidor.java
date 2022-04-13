package a8;

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
               double saldomedio=Double.parseDouble(mensagem);
               double percentualcredito=0;
               if(saldomedio>=0 && saldomedio<=200) {
            	   percentualcredito = 0;
               }else if(saldomedio>=201 && saldomedio<=400) {
            	   percentualcredito = saldomedio*0.2;
               }else if(saldomedio>=401 && saldomedio<=600) {
            	   percentualcredito = saldomedio*0.3;
               }else if(saldomedio>=601) {
            	   percentualcredito = saldomedio*0.4;
               }
               //System.out.println(mensagem);
               outToClient.writeBytes("Saldo Medio: "+saldomedio+", Valor de credito: "+percentualcredito+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}