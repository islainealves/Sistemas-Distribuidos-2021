import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteAplicativo {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Digite a n1: ");
		double n1 = Double.parseDouble(s.nextLine());
		System.out.println("Digite a n2: ");
		double n2 = Double.parseDouble(s.nextLine());
		System.out.println("Digite a n3: ");
		double n3 = Double.parseDouble(s.nextLine());
		
		MediaHome obj = null;
		try {
			obj = (MediaHome) Naming.lookup("//172.31.94.227:4010/MediaHome");
			
			obj.setN1(n1);
			obj.setN2(n2);
			obj.setN3(n3);
			
			System.out.println("O aluno esta: " + obj.calculaMedia());
			
		} catch (Exception ex) {
			System.out.println("ClienteAplicativo exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
}
