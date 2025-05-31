package com.wallet.service.business;

public class ExitProgram implements Business {

    @Override
    public void execute() {
        System.exit(0);
    }
}
