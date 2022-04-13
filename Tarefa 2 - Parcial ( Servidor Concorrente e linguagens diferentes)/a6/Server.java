package a6;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{
    public Socket cliente;

    public Server(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String[] args)  throws IOException{     

        //Cria socket porta 12345
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Aguardando conexões na porta 12345");   

        while (true) {
          Socket cliente = servidor.accept();
          // Cria uma thread
          Server tratamento = new Server(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread
          t.start();
        }
    }

    public void run(){
        try {
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());
            
            //tratamento
            String mensagem = s.nextLine();
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
            
            //devolve mensagem ao cliente
            DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
            outStream.writeUTF("Nome: "+array[0]+", liquido: "+liquido+", nivel: "+array[1]+"\n");
            
            outStream.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}