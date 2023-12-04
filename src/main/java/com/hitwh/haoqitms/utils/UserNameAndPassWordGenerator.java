package com.hitwh.haoqitms.utils;

import java.util.Random;

public class UserNameAndPassWordGenerator {
    public static String generateUserName(String email, String contactInfo) {
        String localPartOfEmail = email.split("@")[0].toLowerCase();
        String extractedContactInfo = extractAlternateCharacters(contactInfo).toLowerCase();
        return localPartOfEmail + extractedContactInfo;
    }

    public static String generatePassWord(String email, String contactInfo) {
        String randomChars = generateRandomString(4);
        String partOfEmail = email.substring(0, Math.min(email.length(), 4)).toLowerCase();
        String partOfContactInfo = extractAlternateCharacters(contactInfo).toUpperCase();
        String password = partOfEmail + partOfContactInfo + randomChars;
        return alternateCase(password);
    }

    private static String extractAlternateCharacters(String text) {
        StringBuilder extracted = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            extracted.append(text.charAt(i));
        }
        return extracted.toString();
    }

    private static String alternateCase(String text) {
        StringBuilder altered = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i % 2 == 0) {
                altered.append(Character.toLowerCase(c));
            } else {
                altered.append(Character.toUpperCase(c));
            }
        }
        return altered.toString();
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        while (length-- > 0) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateUserName("13@456.com", "12345678902"));
        System.out.println(generatePassWord("13@456.com", "12"));
    }
}

