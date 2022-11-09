
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service
            service.addTask(200000);
            service.addTask(200001);
            service.addTask(200002);
            service.addTask(200003);
            service.addTask(200004);
            service.addTask(200005);
            service.addTask(200006);
            service.addTask(200007);
            service.addTask(200008);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);
            service.addTask(-1);

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);

            long start = 0;
            while (true){
                if (service.getSize2() == 17){
                    start = System.currentTimeMillis();
                }

                if (service.getSize() == 9){
                    int sum = 0;
                    while (service.getSize() > 0){
                        sum = sum + service.pollResult();
                    }
                    System.out.println("Sum of Results is: " + sum);
                    break;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("It took " +
                    (end - start) + "ms");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}