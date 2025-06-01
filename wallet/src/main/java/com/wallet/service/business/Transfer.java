package com.wallet.service.business;

import com.wallet.service.models.Transaction;
import com.wallet.service.models.TransactionType;
import com.wallet.service.models.Wallet;
import com.wallet.service.utils.FileUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Transfer implements Business {

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert userID that will transfer the amount: ");
            String fromUserID = scanner.nextLine();

            Wallet fromWallet = (Wallet) FileUtils.read(fromUserID, "wallet.csv", null);

            if (fromWallet != null) {
                BigDecimal checkAmount = BigDecimal.ZERO;

                List<Transaction> transactions = FileUtils.readAll(fromUserID + "/transactions", "transaction")
                        .stream()
                        .map(model -> (Transaction) model)
                        .toList();

                for (Transaction transaction : transactions) {
                    if (transaction.getType() == TransactionType.DEPOSIT) {
                        checkAmount = checkAmount.add(transaction.getAmount());
                    } else if (transaction.getType() == TransactionType.WITHDRAW) {
                        checkAmount = checkAmount.subtract(transaction.getAmount());
                    }
                }

                System.out.println("From wallet initial balance " + fromWallet.getLastUpdate() + " - " + fromWallet.getBalance());

                System.out.println("Insert userID for the account that will receive the transfer: ");
                String toUserID = scanner.nextLine();
                Wallet toWallet = (Wallet) FileUtils.read(toUserID, "wallet.csv", null);

                if (toWallet != null) {
                    System.out.println("Receiving wallet initial balance " + toWallet.getLastUpdate() + " - " + toWallet.getBalance());

                    System.out.println("Enter transfer amount: ");
                    String amount = scanner.nextLine();
                    BigDecimal transferAmount = new BigDecimal(amount);

                    if (transferAmount.compareTo(checkAmount) <= 0) {
                        Transaction transactionTransferred = new Transaction(fromUserID, TransactionType.WITHDRAW, transferAmount);

                        System.out.println("Withdrawing from wallet");
                        transactionTransferred.createTransaction(fromUserID);
                        fromWallet.withdraw(transferAmount);
                        fromWallet.updateWallet(fromUserID);

                        Transaction transactionReceived = new Transaction(toUserID, TransactionType.DEPOSIT, transferAmount);

                        System.out.println("Depositing to wallet");
                        transactionReceived.createTransaction(toUserID);
                        toWallet.deposit(transferAmount);
                        toWallet.updateWallet(toUserID);

                        System.out.println("From wallet final balance " + fromWallet.getLastUpdate() + " - " + fromWallet.getBalance());
                        System.out.println("Receiving wallet final balance " + toWallet.getLastUpdate() + " - " + toWallet.getBalance());
                    } else {
                        System.out.println("Balance insufficient to transfer the selected amount.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
