package a6;

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
			// pega o nome passado pelo usuario
			Scanner s = new Scanner(this.cliente.getInputStream());
			String nome = s.nextLine();

			// cria o socket para conectar ao servidor de banco de dados
			BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));
			Socket client = new Socket("localhost", 2022);
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// envia o nome ao servidor de banco de dados
			outToServer.writeBytes(nome + "\n");

			// recupera os dados do servidor de banco de dados
			String dados[] = new String[3];
			dados = inFromServer.readLine().split("@");

			// calcula salario liquido
			String nivel = dados[0];
			double bruto=Double.parseDouble(dados[1]);
            int dependentes=Integer.parseInt(dados[2]);
            double liquido=0;
            if(nivel.equals("A")) {
         	   if(dependentes>0) {
         		   liquido=bruto-(bruto*0.08);
         	   }else {
         		   liquido=bruto-(bruto*0.03);
         	   }
            }else if(nivel.equals("B")) {
         	   if(dependentes>0) {
         		   liquido=bruto-(bruto*0.1);
         	   }else {
         		   liquido=bruto-(bruto*0.05);
         	   }
            }else if(nivel.equals("C")) {
         	   if(dependentes>0) {
         		   liquido=bruto-(bruto*0.15);
         	   }else {
         		   liquido=bruto-(bruto*0.8);
         	   }
            }else if(nivel.equals("D")) {
         	   if(dependentes>0) {
         		   liquido=bruto-(bruto*0.17);
         	   }else {
         		   liquido=bruto-(bruto*0.1);
         	   }
            }

			// devolve dados do funcionario e salario liquido
			DataOutputStream outStream = new DataOutputStream(this.cliente.getOutputStream());
			outStream.writeUTF("Nome: " + nome + ", nivel: " + nivel + ", salario bruto: " + bruto 
					+ ", quantidade de dependentes: "+ dependentes + ", salario liquido: " + liquido);

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