package Lab_04.Tarjetas_Credito;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreditCardServiceImpl extends UnicastRemoteObject implements CreditCardServiceInterface {
    private Map<String, CreditCard> cardDatabase;

    public CreditCardServiceImpl() throws RemoteException {
        super();
        cardDatabase = new HashMap<>(); //Tarjetas registradas
        // Inicializa algunas tarjetas de crédito prueba
        cardDatabase.put("1234", new CreditCard("1234", "Juan Perez", LocalDate.of(2025, 12, 31), "123", 5000.00, 10000.00));
        cardDatabase.put("5678", new CreditCard("5678", "Maria Lopez", LocalDate.of(2024, 6, 30), "456", 3000.00, 7000.00));
        cardDatabase.put("3456", new CreditCard("3456", "Carlos Ramirez", LocalDate.of(2023, 11, 30), "789", 2000.00, 5000.00));
    }

    @Override
    public boolean authorizeTransaction(String cardNumber, double amount) throws RemoteException {
        //Autoriza la transacion
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null && card.getBalance() >= amount) {
            card.debit(amount);
            return true;
        }
        return false;
    }

    @Override
    public double getBalance(String cardNumber) throws RemoteException {
        //Obtiene el saldo de la tarjeta
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            return card.getBalance();
        }
        return 0.0;
    }

    @Override
    public boolean addFunds(String cardNumber, double amount) throws RemoteException {
        //Añade fondos a la tarjeta
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            card.credit(amount);
            return true;
        }
        return false;
    }

    @Override
    public String printCardDetails(String cardNumber) throws RemoteException {
        //Muestra los detalles de la tarjeta
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            return card.toString();
        }
        return "---> Tarjeta no encontrada";
    }

    @Override
    public void addCreditCard(String cardNumber, String cardHolderName, LocalDate expirationDate, String cvv, double balance, double creditLimit) throws RemoteException {
        //Registrar una nueva tarjeta de credito
        CreditCard newCard = new CreditCard(cardNumber, cardHolderName, expirationDate, cvv, balance, creditLimit);
        cardDatabase.put(cardNumber, newCard);
    }

    @Override
    public boolean existsCreditCard(String cardNumber) throws RemoteException {
        //Verifica el nro de tarjeta es valido
        return cardDatabase.get(cardNumber) != null;
    }
}