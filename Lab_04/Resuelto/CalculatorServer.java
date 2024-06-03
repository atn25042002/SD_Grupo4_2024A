import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {

    public CalculatorServer() {
        try {
            System.setProperty("java.rmi.server.hostname","192.168.56.1");
            LocateRegistry.createRegistry(1099);
            Calculator c = new CalculatorImpl();
        Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new CalculatorServer();
    }   

}