package Lab_04.Tarjetas_Credito;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CreditCardServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            CreditCardServiceInterface service = new CreditCardServiceImpl();

            Naming.rebind("rmi://localhost:1099/CreditCardService", service);

            System.out.println("CreditCardService is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}