package utilitiesConfig;

import java.util.Random;
import java.util.UUID;

public class RandomDataUtils {
    private static RandomDataUtils instance;
    private final Random rand;

    private RandomDataUtils() {
        rand = new Random();
    }

    public static RandomDataUtils getData() {
        if (instance == null) {
            instance = new RandomDataUtils();
        }
        return instance;
    }

    public int getRandomNumber(int bound) {
        return rand.nextInt(bound);
    }

    public int getRandom4Digits() {
        return rand.nextInt(9000) + 1000;
    }

    public String getRandomEmail() {
        return "automation" + getRandom4Digits() + "@mail.com";
    }
    public String getRandomEmail(String domain) {
        return "test" + getRandom4Digits() + "@" + domain; // ví dụ: @mailinator.com
    }
    public String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public String getRandomPhone() {
        String[] prefixes = {"09", "08", "03", "05", "07"};
        String prefix = prefixes[rand.nextInt(prefixes.length)];
        return prefix + (10000000 + rand.nextInt(90000000));
    }

    public String getRandomFullName() {
        return getRandomString(5) + " " + getRandomString(7);
    }

    public String getRandomPassword() {
        return getRandomString(8) + getRandom4Digits();
    }

    public String getRandomUsername() {
        return "user" + getRandom4Digits();
    }

    public String getRandomDate() {
        int day = rand.nextInt(28) + 1;
        int month = rand.nextInt(12) + 1;
        int year = rand.nextInt(24) + 2000;
        return "%02d/%02d/%04d".formatted(day, month, year);
    }
    public String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
    public static void sleepInSecond(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static String getFormatCurrency(long amount) {
        return String.format("%,d", amount);
    }
}