package ru.netology.data;

import lombok.Value;

public class TestUser {
    private TestUser() {
    }

    @Value
    public static class AuthInfo {
        public String login;
        public String password;
    }

    public static AuthInfo getAutInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        public String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        public String number;
    }

    public static CardNumber getCard1Number() {
        return new CardNumber("5559 0000 0000 0001");
    }
    public static CardNumber getCard2Number() {
        return new CardNumber("5559 0000 0000 0002");
    }
}