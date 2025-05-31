package com.wallet.service.models;

import com.wallet.service.utils.FileUtils;

import java.io.IOException;

public class User implements Model {
    private String id;
    private String name;
    private Wallet wallet;

    public User(String id, String username, Wallet wallet) {
        this.id = id;
        this.name = username;
        this.wallet = wallet;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.wallet = null;
    }

    User() {
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

    @Override
    public Model fromString(String line) {
        String[] parts = line.split(";");
        return new User(parts[0], parts[1]);
    }
}
