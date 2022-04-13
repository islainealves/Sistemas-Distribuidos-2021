package a2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

   public static void main(String[] args) {

       try {
            BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));
            Socket client = new Socket("localhost", 40000);
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String mensagem = inFromUSer.readLine()+"@";
            mensagem += inFromUSer.readLine()+"@";
            mensagem += inFromUSer.readLine();
            outToServer.writeBytes(mensagem +"\n");
            mensagem = inFromServer.readLine();
            System.out.println(mensagem);
            client.close();   
       } catch (UnknownHostException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   } 
}