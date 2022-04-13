package a2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ApplicationServer implements Runnable {
	public Socket cliente;

	public ApplicationServer(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String[] args) throws IOException {

		// Cria socket na porta 2021
		ServerSocket servidor = new ServerSocket(2021);
		System.out.println("Aguardando conexões na porta 2021");

		while (true) {
			Socket cliente = servidor.accept();

			// Cria uma thread
			ApplicationServer tratamento = new ApplicationServer(cliente);
			Thread t = new Thread(tratamento);

			// Inicia a thread
			t.start();
		}
	}

	public void run() {
		try {
			// pega o nome da pessoa passado pelo usuario
			Scanner s = new Scanner(this.cliente.getInputStream());
			String nome = s.nextLine();

			// cria o socket para conectar ao servidor de banco de dados
			Socket client = new Socket("localhost", 2022);
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// envia o nome ao servidor de banco de dados
			outToServer.writeBytes(nome + "\n");

			// recupera os dados da pessoa do servidor de banco de dados
			String dados[] = new String[3];
			dados = inFromServer.readLine().split("@");

			// verifica se e maior de idade
            String mensagem = null;
            
            int idade=Integer.parseInt(dados[2]);
            if(dados[1].equals("masculino") && idade>=18) {
         	   mensagem="maior de idade";
            }else if(dados[1].equals("feminino") && idade>=21) {
         	   mensagem="maior de idade";
            }else {
         	   mensagem="menor de idade";
            }

			// devolve o nome e a mensagem
			DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
			outStream.writeUTF(dados[0] + " e " + mensagem);

			// finaliza as variaveis
			outStream.close();
			s.close();
			this.cliente.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}