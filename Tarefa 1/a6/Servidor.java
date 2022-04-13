package a6;

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
               String array[] = new String[4];
               array = mensagem.split("@");
               
               double bruto=Double.parseDouble(array[2]);
               int dependentes=Integer.parseInt(array[3]);
               double liquido=0;
               if(array[1].equals("A")) {
            	   if(dependentes>0) {
            		   liquido=bruto-(bruto*0.08);
            	   }else {
            		   liquido=bruto-(bruto*0.03);
            	   }
               }else if(array[1].equals("B")) {
            	   if(dependentes>0) {
            		   liquido=bruto-(bruto*0.1);
            	   }else {
            		   liquido=bruto-(bruto*0.05);
            	   }
               }else if(array[1].equals("C")) {
            	   if(dependentes>0) {
            		   liquido=bruto-(bruto*0.15);
            	   }else {
            		   liquido=bruto-(bruto*0.8);
            	   }
               }else if(array[1].equals("D")) {
            	   if(dependentes>0) {
            		   liquido=bruto-(bruto*0.17);
            	   }else {
            		   liquido=bruto-(bruto*0.1);
            	   }
               }
               //System.out.println(mensagem);
               outToClient.writeBytes("Nome: "+array[0]+", liquido: "+liquido+", nivel: "+array[1]+"\n");    
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}