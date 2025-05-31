package com.wallet.service.business;

import com.wallet.service.models.User;
import com.wallet.service.models.Wallet;

import java.util.Scanner;

public class CreateWallet implements Business {

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String name = sc.nextLine();
        System.out.println("Enter user ID: ");
        String id = sc.nextLine();
        Wallet wallet = new Wallet();
        User user = new User(id, name, wallet);

        try {
            user.createUser();
            wallet.createWallet(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
