package com.wallet.service.models;

import com.wallet.service.utils.FileUtils;

import java.io.IOException;

public class User {
    private final String id;
    private final String name;
    private final Wallet wallet;

    public User(String id, String username, Wallet wallet) {
        this.id = id;
        this.name = username;
        this.wallet = wallet;
    }

    public void createUser() throws IOException {
        String content = id + ";" + name;
        FileUtils.write(id, "user.csv", content);
    }

    private String getUsername() {
        return name;
    }

    private Wallet getWallet() {
        return wallet;
    }

}
