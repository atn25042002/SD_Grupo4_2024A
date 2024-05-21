package Lab_04.Tarjetas_Credito;

import java.time.LocalDate;

public class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expirationDate;
    private String cvv;
    private double balance;
    private double creditLimit;

    public CreditCard(String cardNumber, String cardHolderName, LocalDate expirationDate, String cvv, double balance, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public double getBalance() {
        return balance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void debit(double amount) {
        if (balance + amount <= creditLimit) {
            this.balance -= amount;
        } else {
            System.out.println("Error: Exceeds credit limit");
        }
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Numero de tarjeta: " + cardNumber +
               "\nTitular de la tarjeta: " + cardHolderName +
               "\nFecha de expiracion: " + expirationDate +
               "\nCVV: " + cvv +
               "\nSaldo: " + balance +
               "\nLimite de credito: " + creditLimit;
    }
}
