package a8;
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
            
            //devolve mensagem ao cliente
            DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
            outStream.writeUTF("Saldo Medio: "+saldomedio+", Valor de credito: "+percentualcredito+"\n");
            
            outStream.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}