package Lab_04.Tarjetas_Credito;

import java.rmi.Naming;
import java.util.Scanner;

public class CreditCardClient {
    public static void main(String[] args) {
        try {
            CreditCardServiceInterface service = (CreditCardServiceInterface) Naming.lookup("rmi://localhost:1099/CreditCardService");

            Scanner scanner = new Scanner(System.in);
            System.out.println("- Selecciona una opcion:\n1: Autorizar Transaccion\n2: Consultar Saldo\n3: Agregar fondos\n4: Mostrar detalles de la tarjeta");
            int choice = scanner.nextInt();

            System.out.print("- Ingrese número de tarjeta: ");
            String cardNumber = scanner.next();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el monto a autorizar: ");
                    double amount = scanner.nextDouble();
                    boolean authorized = service.authorizeTransaction(cardNumber, amount);
                    System.out.println("Transaccion autorizada: " + authorized);
                    break;
                case 2:
                    double balance = service.getBalance(cardNumber);
                    System.out.println("Saldo Actual: " + balance);
                    break;
                case 3:
                    System.out.print("Ingrese el monto a agregar: ");
                    double funds = scanner.nextDouble();
                    boolean added = service.addFunds(cardNumber, funds);
                    System.out.println("Fondos agregados: " + added);
                    break;
                case 4:
                    String details = service.printCardDetails(cardNumber);
                    System.out.println(details);
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}