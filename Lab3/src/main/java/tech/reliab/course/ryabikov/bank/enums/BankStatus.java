package tech.reliab.course.ryabikov.bank.enums;

import java.util.Random;

public enum BankStatus {
    WORKING,
    NOT_WORKING,
    NO_MONEY;

    private static final Random RANDOM = new Random();

    public static BankStatus getRandomStatus() {
        BankStatus[] statuses = values();
        return statuses[RANDOM.nextInt(statuses.length)];
    }
}