package com.wallet.service.models;

import com.wallet.service.utils.FileUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Wallet implements Model {
    private final LocalDate openDate;
    private BigDecimal balance;
    private LocalDate lastUpdate;

    public Wallet() {
        this.openDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.balance = BigDecimal.ZERO;
    }

    public Wallet(LocalDate openDate, LocalDate lastUpdate, BigDecimal balance) {
        this.openDate = openDate;
        this.lastUpdate = lastUpdate;
        this.balance = balance;
    }

    public void createWallet(String id) throws IOException {
        save(id);
    }

    public void updateWallet(String id) throws IOException {
        save(id);
    }

    private void save(String id) throws IOException {
        String content = openDate.toString() + ";" + lastUpdate.toString() + ";" + balance.toString();
        FileUtils.write(id, "wallet.csv", content);
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public Model fromString(String line) {
        String[] parts = line.split(";");
        return new Wallet(LocalDate.parse(parts[0]), LocalDate.parse(parts[1]), new BigDecimal(parts[2]));
    }
}
