
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue1;
    private final BlockingQueue<Integer> queue2;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue1 = new LinkedBlockingQueue<>();
        this.queue2 = new LinkedBlockingQueue<>();
    }

    @Override
    public int pollTask() throws RemoteException {
        return this.queue1.poll();
    }

    public int pollResult() throws RemoteException {
        return this.queue2.poll();
    }

    @Override
    public void addTask(int input) throws RemoteException {
        this.queue1.add(input);
        System.out.println("Added task: " + input);
    }

    public void addResult(int input) throws RemoteException {
        this.queue2.add(input);
        System.out.println("Added result: " + input);
    }

    public int getSize() throws RemoteException{
        return this.queue2.size();
    }

    public int getSize2() throws RemoteException{
        return this.queue1.size();
    }
}
