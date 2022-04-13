package a1;

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
               
               double salario=Double.parseDouble(array[2]);
               if(array[1].equals("operador")) {
               	salario = (salario*1.2);
               } else if(array[1].equals("programador")) {
               	salario = (salario*1.18);
               }
               mensagem=array[0]+" "+array[1]+" "+salario;
               //System.out.println(mensagem);
               outToClient.writeBytes(mensagem+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}