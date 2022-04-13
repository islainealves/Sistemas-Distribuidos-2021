import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MediaServer extends UnicastRemoteObject implements MediaHome {
	private double n1,n2,n3;

	public MediaServer() throws RemoteException {
		super();
	}

	public void setN1(double n1) {
		this.n1 = n1;
	}
	
	public void setN2(double n2) {
		this.n2 = n2;
	}
	
	public void setN3(double n3) {
		this.n3 = n3;
	}

	public String calculaMedia() {
		String mensagem = "";
		double media=(n1+n2)/2;
		
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
        //System.out.println(mensagem);

		return mensagem;
	}

	public static void main(String args[]) {

		try {
			MediaHome obj = new MediaServer();
			Registry registry = LocateRegistry.createRegistry(4010);
			Naming.rebind("//172.31.94.227:4010/MediaHome", obj);
			System.out.println("MediaServer bound in registry");
		} catch (Exception e) {
			System.out.println("MediaServer err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
