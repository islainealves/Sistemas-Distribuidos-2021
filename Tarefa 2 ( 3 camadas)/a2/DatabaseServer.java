package a2;

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
	               
	               //recebe o nome do funcionario do servidor de aplicacao
	               String nome = inFromClient.readLine();
	               
	               //consulta o nome no banco de dados e recebe os dados da pessoa
	               Object[] dados = consultar(nome);
	               
	               //devolve os dados da pessoa ao servidor de aplicacao
	               outToClient.writeBytes(dados[0]+"@"+dados[1]+"@"+dados[2]+"\n");    
	           } 
	      
	       } catch (IOException e) {
	           e.printStackTrace();
	       }  
	   }
	
	public static Object[] consultar(String nome) {
		String url = "jdbc:postgresql://localhost:5432/ListaSD";
		String usuario = "postgres";
		String senha = "2859";
		
		String sexo = null;
		int idade = 0;
		try {
			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			
			Statement stm = (Statement) conexao.createStatement();
			
			String select = "SELECT nome, sexo, idade FROM public.exercicio2 WHERE nome='"+nome+"';";

			ResultSet rs = stm.executeQuery(select);
            
            while (rs.next()) {
                sexo = rs.getString(2);
                idade = rs.getInt(3);
            }
            
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Object[] dados = { nome, sexo, idade }; 
		return dados;
	}
}
