package com.e.commerce.application.domain.utils.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomAnyString {
    private static final Random random = new Random();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private RandomAnyString() {}

    /**
     * Generate random String can be used for key, secret
     *
     * @param len - input your len sight
     * @return - String
     */
    public static String generateRandomString(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                + "jklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(random.nextInt(chars.length())));
        return sb.toString();
    }

    /**
     * Generate next value increment
     *
     * @param name - using name combine with value
     * @return - string
     */
    public static String generateCounterIncrement(String name) {
        int nextId = idCounter.getAndIncrement();
        String date = LocalDateTime.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        return name + nextId + date.formatted(formatter);
    }

    /**
     * Generate next value increment
     *
     * @param length - using name combine with value
     * @return - string
     */
    public static String generateUUIDString(int length) {
        String randomString = UUID.randomUUID().toString().replace("-", "");
        return randomString.substring(0, length);
    }
}