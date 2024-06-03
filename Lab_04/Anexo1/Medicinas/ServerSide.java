import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class ServerSide {
	public static void main(String[] args) throws Exception {
		// Configura el hostname para el servidor RMI
		System.setProperty("java.rmi.server.hostname", "192.168.56.1");

		// Inicia el registro RMI en el puerto 1099 (puerto predeterminado)
		LocateRegistry.createRegistry(1099);
		Stock pharmacy = new Stock();
		pharmacy.addMedicine("Paracetamol", 3.2f, 10);
		pharmacy.addMedicine("Mejoral", 2.0f, 20);
		pharmacy.addMedicine("Amoxilina", 1.0f, 30);
		pharmacy.addMedicine("Aspirina", 5.0f, 40);
		Naming.rebind("rmi://localhost:1099/PHARMACY", pharmacy);
		System.out.println("Server ready");
	}
}