package Lab_04.Tarjetas_Credito;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreditCardServiceInterface extends Remote {
    boolean authorizeTransaction(String cardNumber, double amount) throws RemoteException;
    double getBalance(String cardNumber) throws RemoteException;
    boolean addFunds(String cardNumber, double amount) throws RemoteException;
    String printCardDetails(String cardNumber) throws RemoteException;
}