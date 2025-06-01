package com.wallet.service.business;

public enum BusinessStrategy {
    EXIT_PROGRAM(new ExitProgram(), "exit"),
    CREATE_WALLET(new CreateWallet(), "new"),
    DEPOSIT(new Deposit(), "deposit"),
    WITHDRAW(new Withdraw(), "withdraw"),
    TRANSFER(new Transfer(), "transfer");

    private final Business business;
    private final String command;

    BusinessStrategy(Business business, String command) {
        this.business = business;
        this.command = command;
    }

    public static Business findBusiness(String command) {
        for (BusinessStrategy strategy : BusinessStrategy.values()) {
            if (strategy.getCommand().equals(command)) {
                return strategy.getBusiness();
            }
        }
        return null;
    }

    public Business getBusiness() {
        return this.business;
    }

    public String getCommand() {
        return this.command;
    }
}
