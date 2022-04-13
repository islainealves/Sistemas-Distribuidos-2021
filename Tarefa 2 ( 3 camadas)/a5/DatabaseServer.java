package a5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseServer {
	
	public static void main(String[] args) {
		  
	       try {
	    	   //cria o socket
	           ServerSocket socket = new ServerSocket(2022);
	           System.out.println("Aguardando conexões na porta 2022");
	           
	           while(true) {
	        	   //aceita a conexão
	               Socket connectionSocket = socket.accept();
	               
	               BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	               DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	               
	               //recebe o nome do servidor de aplicacao
	               String nome = inFromClient.readLine();
	               
	               //consulta o nome no banco de dados e retorna o resultado
	               int idade = consultar(nome);
	               
	               //devolve os dados ao servidor de aplicacao
	               outToClient.writeBytes(idade+"\n");    
	           } 
	      
	       } catch (IOException e) {
	           e.printStackTrace();
	       }  
	   }
	
	public static int consultar(String nome) {
		String url = "jdbc:postgresql://localhost:5432/ListaSD";
		String usuario = "postgres";
		String senha = "2859";
		
		int idade = 0;
		try {
			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			
			Statement stm = (Statement) conexao.createStatement();
			
			String select = "SELECT idade FROM public.exercicio5 WHERE nome='"+nome+"';";

			ResultSet rs = stm.executeQuery(select);
            
            while (rs.next()) {
                idade = rs.getInt(1);
            }
            
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idade;
	}
}
