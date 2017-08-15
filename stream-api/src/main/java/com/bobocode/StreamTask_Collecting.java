package com.bobocode;


import com.bobocode.model.Account;
import com.bobocode.util.TestDataProvider;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Using Stream API, lambdas, and predefined functional interfaces implement the code according to requirements
 */
public class StreamTask_Collecting {
    public static void main(String[] args) {
        List<Account> accounts = TestDataProvider.generateAccountList();

        Consumer print = System.out::println;


        System.out.println("\nFind a set of account owner's names(first name) that have a length <= 4");
        accounts.stream()
                .map(Account::getFirstName)
                .filter(name -> name.length() <= 4)
                .collect(Collectors.toSet())
                .forEach(print);


        System.out.println("\nGroup all accounts by its email providers. (You can consider as an email provider everything that goes after '@'");
        accounts.stream()
                .collect(Collectors.groupingBy(a -> a.getEmail().substring(a.getEmail().indexOf("@") + 1)))
                .entrySet()
                .forEach(print);


        System.out.println("\nFind the richest guy");
        accounts.stream()
                .max(Comparator.comparing(Account::getBalance))
                .ifPresent(print);


        System.out.println("\nFind the richest guy for each email provider");
        accounts.stream()
                .collect(Collectors.groupingBy(account -> account.getEmail().substring(account.getEmail().indexOf("@") + 1)))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue().stream().max(Comparator.comparing(Account::getBalance)))
                .collect(Collectors.toList())
                .forEach(print);


        System.out.println("\nSplit accounts by their balance (those who have more than $90 000.00, and those who don't)");
        accounts.stream()
                .collect(Collectors.partitioningBy(account -> account.getBalance().doubleValue() > 90_000.00))
                .entrySet()
                .forEach(print);
    }
}