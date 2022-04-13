package a5;
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
            
            //devolve mensagem ao cliente
            DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
            outStream.writeUTF(categoria+"\n");
            
            outStream.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}