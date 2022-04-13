package a2;
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
            
            //devolve mensagem ao cliente
            DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
            outStream.writeUTF(array[0]+": "+mensagem+"\n");
            
            outStream.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}