
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a service which will be accessible remotely
 */
public interface Service extends Remote {
    int pollTask() throws RemoteException;
    int pollResult() throws RemoteException;
    void addTask(int input) throws RemoteException;
    void addResult(int input) throws RemoteException;
    int getSize() throws RemoteException;
    int getSize2() throws RemoteException;

}
