package com.wallet.service.business;

import com.wallet.service.models.Transaction;
import com.wallet.service.models.TransactionType;
import com.wallet.service.models.Wallet;
import com.wallet.service.utils.FileUtils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Deposit implements Business {

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter user ID:");
            String userId = scanner.nextLine();

            Wallet wallet = (Wallet) FileUtils.read(userId, "wallet.csv");

            if (wallet != null) {
                System.out.println(wallet.getLastUpdate() + " - " + wallet.getBalance());

                System.out.print("Enter deposit amount: ");
                String amount = scanner.nextLine();
                BigDecimal depositAmount = new BigDecimal(amount);

                Transaction transaction = new Transaction(userId, TransactionType.DEPOSIT, depositAmount);
                transaction.createTransaction(userId);

                wallet.deposit(depositAmount);
                wallet.updateWallet(userId);

                System.out.println(wallet.getLastUpdate() + " - " + wallet.getBalance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
