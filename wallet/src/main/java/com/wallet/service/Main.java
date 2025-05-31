package com.wallet.service;

import com.wallet.service.business.Business;
import com.wallet.service.business.BusinessStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Insert your option:");
            System.out.println("exit - to end the program.");

            String enter = scanner.nextLine();

            Business business = BusinessStrategy.findBusiness(enter);

            if (business!=null) {
                business.execute();
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
