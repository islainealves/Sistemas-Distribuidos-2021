import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MediaHome extends Remote {
	String calculaMedia() throws RemoteException;
    void setN1(double n1) throws RemoteException;
    void setN2(double n2) throws RemoteException;
    void setN3(double n3) throws RemoteException;
}
