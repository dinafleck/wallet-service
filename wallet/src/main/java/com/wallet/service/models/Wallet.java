package com.wallet.service.models;

import com.wallet.service.utils.FileUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class Wallet {
    private final Date openDate;
    private final BigDecimal balance;
    private Date lastUpdate;

    public Wallet() {
        this.openDate = new Date();
        this.lastUpdate = new Date();
        this.balance = BigDecimal.ZERO;
    }

    public void createWallet(String id) throws IOException {
        String content = openDate.toString() + ";" + lastUpdate.toString() + ";" + balance.toString();
        FileUtils.write(id, "wallet.csv", content);
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
