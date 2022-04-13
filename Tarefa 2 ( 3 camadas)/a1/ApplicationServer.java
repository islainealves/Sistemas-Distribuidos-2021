package a1;

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
			ApplicationServer ap = new ApplicationServer(cliente);
			Thread t = new Thread(ap);

			// Inicia a thread
			t.start();
		}
	}

	public void run() {
		try {
			// pega o nome do funcionario passado pelo usuario
			Scanner s = new Scanner(this.cliente.getInputStream());
			String nomeFuncionario = s.nextLine();

			// cria o socket para conectar ao servidor de banco de dados
			BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));
			Socket client = new Socket("localhost", 2022);
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// envia o nome ao servidor de banco de dados
			outToServer.writeBytes(nomeFuncionario + "\n");

			// recupera os dados do funcionario do servidor de banco de dados
			String dados[] = new String[3];
			dados = inFromServer.readLine().split("@");

			// ajusta o salario
			double salario = Float.parseFloat(dados[2]);
			if (dados[1].equals("operador")) {
				salario = (salario * 1.2);
			} else if (dados[1].equals("programador")) {
				salario = (salario * 1.18);
			}

			// devolve o nome, cargo e salario ajustado ao cliente
			DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
			outStream.writeUTF("Nome: " + dados[0] + ", Cargo: " + dados[1] + ", salario ajustado: " + salario);

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