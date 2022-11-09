
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (true) {
                int input = service.pollTask();
                if (input == -1) {
                    System.out.println("No more work");
                    break;
                } else {
                    System.out.println("Received: " + input);

                    int N = input;
                    int x, y, flg;
                    int k = 0;

                    // Printing display message

                    for (x = 1; x <= N; x++) {

                        if (x == 1 || x == 0) {
                            continue;
                        }

                        flg = 1;

                        for (y = 2; y <= x / 2; ++y) {
                            if (x % y == 0) {
                                flg = 0;
                                break;
                            }
                        }

                        if (flg == 1)
                            k = k + 1;
                    }

                    System.out.println("Requesting new work");

                    service.addResult(k);
                    continue;
                }
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
