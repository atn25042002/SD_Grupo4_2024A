package Lab_04.Tarjetas_Credito;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class CreditCardServiceImpl extends UnicastRemoteObject implements CreditCardServiceInterface {
    private Map<String, CreditCard> cardDatabase;

    public CreditCardServiceImpl() throws RemoteException {
        super();
        cardDatabase = new HashMap<>();
        // Inicializa algunas tarjetas de crédito con saldo
        cardDatabase.put("1234-5678-9012-3456", new CreditCard("1234-5678-9012-3456", 500.00));
        cardDatabase.put("9876-5432-1098-7654", new CreditCard("9876-5432-1098-7654", 1000.00));
    }

    @Override
    public boolean authorizeTransaction(String cardNumber, double amount) throws RemoteException {
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null && card.getBalance() >= amount) {
            card.debit(amount);
            return true;
        }
        return false;
    }

    @Override
    public double getBalance(String cardNumber) throws RemoteException {
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            return card.getBalance();
        }
        return 0.0;
    }

    @Override
    public boolean addFunds(String cardNumber, double amount) throws RemoteException {
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            card.credit(amount);
            return true;
        }
        return false;
    }

    @Override
    public String printCardDetails(String cardNumber) throws RemoteException {
        CreditCard card = cardDatabase.get(cardNumber);
        if (card != null) {
            return card.toString();
        }
        return "Tarjeta no encontrada";
    }
}

class CreditCard {
    private String cardNumber;
    private double balance;

    public CreditCard(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Numero de tarjeta: " + cardNumber + "\nSaldo: " + balance;
    }
}