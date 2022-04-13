package a1;
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
            
            double salario=Double.parseDouble(array[2]);
            if(array[1].equals("operador")) {
            	salario = (salario*1.2);
            } else if(array[1].equals("programador")) {
            	salario = (salario*1.18);
            }
            mensagem=array[0]+" "+array[1]+" "+salario;
            
            //devolve mensagem ao cliente
            DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
            outStream.writeUTF(mensagem+"\n");
            
            outStream.close();
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}