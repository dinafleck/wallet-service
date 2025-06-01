package com.wallet.service.models;

import com.wallet.service.utils.FileUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction implements Model {
    private int id;
    private LocalDate date;
    private TransactionType type;
    private BigDecimal amount;

    public Transaction() {

    }

    public Transaction(String userId, TransactionType type, BigDecimal amount) {
        this.id = FileUtils.next(userId + "/transactions");
        this.date = LocalDate.now();
        this.type = type;
        this.amount = amount;
    }

    public Transaction(int id, LocalDate date, TransactionType type, BigDecimal amount) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public void createTransaction(String userId) throws IOException {
        String content = id + ";" + date.toString() + ";" + type.toString() + ";" + amount.toString();
        FileUtils.write(userId + "/transactions", id + ".csv", content);
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Model fromString(String line) {
        String[] parts = line.split(";");
        return new Transaction(
                Integer.parseInt(parts[0]),
                LocalDate.parse(parts[1]),
                TransactionType.valueOf(parts[2]),
                new BigDecimal(parts[3])
        );
    }
}
