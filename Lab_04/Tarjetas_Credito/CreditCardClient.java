package Lab_04.Tarjetas_Credito;

import java.rmi.Naming;
import java.time.LocalDate;
import java.util.Scanner;

public class CreditCardClient {
    public static void main(String[] args) {
        try {
            CreditCardServiceInterface service = (CreditCardServiceInterface) Naming.lookup("rmi://localhost:1099/CreditCardService");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("----------------------------------------------");
                System.out.println("\tSISTEMA DE TARJETAS DE CREDITO");
                System.out.println("- Selecciona una opcion:");
                System.out.println("1: Autorizar Transaccion");
                System.out.println("2: Consultar Saldo");
                System.out.println("3: Agregar fondos");
                System.out.println("4: Mostrar detalles de la tarjeta");
                System.out.println("5: Agregar una nueva tarjeta de crédito");
                System.out.println("6: Salir");
                System.out.print("- Ingrese la operacion que desea ejecutar: ");
                int choice = scanner.nextInt();

                if(choice == 6){
                    System.out.println("---> Saliendo...");
                    break;
                }

                System.out.print("- Ingrese número de tarjeta: ");
                String cardNumber = scanner.next();

                boolean validCard= service.existsCreditCard(cardNumber);
                if(choice <5){                    
                    if(validCard){
                        switch (choice) {
                            case 1:
                                System.out.print("Ingrese el monto a autorizar: ");
                                double amount = scanner.nextDouble();
                                boolean authorized = service.authorizeTransaction(cardNumber, amount);
                                System.out.println("---> Transaccion autorizada: " + authorized);
                                break;
                            case 2:
                                double balance = service.getBalance(cardNumber);
                                System.out.println("---> Saldo Actual: " + balance);
                                break;
                            case 3:
                                System.out.print("Ingrese el monto a agregar: ");
                                double funds = scanner.nextDouble();
                                boolean added = service.addFunds(cardNumber, funds);
                                System.out.println("---> Fondos agregados: " + added);
                                break;
                            case 4:
                                String details = service.printCardDetails(cardNumber);
                                System.out.println("---> Mostrando Detalles:");
                                System.out.println(details);
                                break;
                            default:
                            System.out.println("---> Opcion invalida.");
                        }
                    }else{
                        System.out.println("---> Tarjeta no encontrada");
                    }
                }else{
                    System.out.print("- Ingrese el nombre del titular: ");
                    scanner.nextLine(); // Consume newline
                    String cardHolderName = scanner.nextLine();
                    System.out.print("- Ingrese la fecha de expiración (YYYY-MM-DD): ");
                    LocalDate expirationDate = LocalDate.parse(scanner.next());
                    System.out.print("- Ingrese el CVV: ");
                    String cvv = scanner.next();
                    System.out.print("- Ingrese el saldo inicial: ");
                    double initialBalance = scanner.nextDouble();
                    System.out.print("- Ingrese el límite de crédito: ");
                    double creditLimit = scanner.nextDouble();
                    service.addCreditCard(cardNumber, cardHolderName, expirationDate, cvv, initialBalance, creditLimit);
                    System.out.println("---> Tarjeta de crédito agregada exitosamente.");
                }
                System.out.print("¿Desea realizar otra operación? (y/n): ");
                String opcion= scanner.next();
                if(!opcion.equals("y")){
                    break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void mostrarResultado(boolean valid, String message){
        if(valid){
            System.out.println(message);
        }else{
            System.out.println("Tarjeta no encontrada");
        }
    }
}