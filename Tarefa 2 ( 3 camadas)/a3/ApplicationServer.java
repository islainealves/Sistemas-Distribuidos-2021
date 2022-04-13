package a3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			// pega o nome do aluno passado pelo usuario
			Scanner s = new Scanner(this.cliente.getInputStream());
			String nome = s.nextLine();

			// cria o socket para conectar ao servidor de banco de dados
			BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));
			Socket client = new Socket("localhost", 2022);
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// envia o nome ao servidor de banco de dados
			outToServer.writeBytes(nome + "\n");

			// recupera os dados do aluno do servidor de banco de dados
			String dados[] = new String[3];
			dados = inFromServer.readLine().split("@");

			// calcula media
			String mensagem = null;
			float n1=Float.parseFloat(dados[0]);
            float n2=Float.parseFloat(dados[1]);
            float n3=Float.parseFloat(dados[2]);
            float media=(n1+n2)/2;
            if(media>=7) {
         	   mensagem="Aprovado";
            }else if(media>=3) {
         	   media=(media+n3)/2;
         	   if(media>=5) {
         		   mensagem="Aprovado";
         	   }else {
         		   mensagem="Reprovado";
         	   }
            }else {
         	   mensagem="Reprovado";
            }

			// devolve nome, media e a mensagem
			DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
			outStream.writeUTF(nome + ", media: " + media + " : " + mensagem);

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