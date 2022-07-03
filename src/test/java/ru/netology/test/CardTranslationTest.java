package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.TestUser;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTranslationTest {
    int overBalance;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void testFrom2to1card() {
        var loginPage = new LoginPage();
        var authInfo = TestUser.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = TestUser.getVerificationCode(authInfo);
        var cardPage = verificationPage.validCode(verificationCode);
        String amountAdded = "1000";
        val balanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        val cardTranslation = cardPage.translationCard(TestUser.getCard1Number().getNumber());
        cardTranslation.translationCard(amountAdded, TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard1 + amountAdd, newBalanceCard1);
        assertEquals(balanceCard2 - amountAdd, newBalanceCard2);
    }

    @Test
    void testFrom1to2card() {
        var loginPage = new LoginPage();
        var authInfo = TestUser.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = TestUser.getVerificationCode(authInfo);
        var cardPage = verificationPage.validCode(verificationCode);
        String amountAdded = "1000";
        val balanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        val balanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        val cardTranslation = cardPage.translationCard(TestUser.getCard2Number().getNumber());
        cardTranslation.translationCard(amountAdded, TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard2 + amountAdd, newBalanceCard2);
        assertEquals(balanceCard1 - amountAdd, newBalanceCard1);
    }

    @Test
    void testOverBalance() {
        var loginPage = new LoginPage();
        var authInfo = TestUser.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = TestUser.getVerificationCode(authInfo);
        var cardPage = verificationPage.validCode(verificationCode);
        val balanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        overBalance = balanceCard2 + 1000;
        val cardTranslation = cardPage.translationCard(TestUser.getCard1Number().getNumber());
        cardTranslation.translationCard(String.valueOf(overBalance), TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = cardPage.balanceCard(TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = cardPage.balanceCard(TestUser.getCard2Number().getNumber());
        assertEquals(balanceCard1, newBalanceCard1);
        assertEquals(balanceCard2, newBalanceCard2);
        cardTranslation.errorTranslation();
    }
}