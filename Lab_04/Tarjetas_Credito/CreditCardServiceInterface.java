package Lab_04.Tarjetas_Credito;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface CreditCardServiceInterface extends Remote {
    boolean authorizeTransaction(String cardNumber, double amount) throws RemoteException;
    double getBalance(String cardNumber) throws RemoteException;
    boolean addFunds(String cardNumber, double amount) throws RemoteException;
    String printCardDetails(String cardNumber) throws RemoteException;
    void addCreditCard(String cardNumber, String cardHolderName, LocalDate expirationDate, String cvv, double balance, double creditLimit) throws RemoteException;
    boolean existsCreditCard(String cardNumber) throws RemoteException;
}