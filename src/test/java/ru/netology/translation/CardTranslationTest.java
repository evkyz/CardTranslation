package ru.netology.translation;

import com.codeborne.selenide.selector.ByText;
import lombok.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTranslationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = TestUser.getAutInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = TestUser.getVerificationCode(authInfo);
        var cardPage = verificationPage.validCode(verificationCode);
    }

    @Test
    void testFrom2to1card() {
        String amountAdded = "1000";
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard1Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard1 + amountAdd, newBalanceCard1);
        assertEquals(balanceCard2 - amountAdd, newBalanceCard2);
        $(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testFrom1to2card() {
        String amountAdded = "1000";
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard2Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard2 + amountAdd, newBalanceCard2);
        assertEquals(balanceCard1 - amountAdd, newBalanceCard1);
        $(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testFrom2to1cardOver10_000() {
        String amountAdded = "10100";
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard1Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard1 + amountAdd, newBalanceCard1);
        assertEquals(balanceCard2 - amountAdd, newBalanceCard2);
        $(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testFrom1to2cardOver10_000() {
        String amountAdded = "10100";
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard2Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard1Number().getNumber());
        val newBalanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val newBalanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        int amountAdd = Integer.parseInt(amountAdded);
        assertEquals(balanceCard2 + amountAdd, newBalanceCard2);
        assertEquals(balanceCard1 - amountAdd, newBalanceCard1);
        $(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testFrom2to1cardNull() {
        String amountAdded = "0";
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard1Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard2Number().getNumber());
        $(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testFrom1to1card() {
        String amountAdded = "100";
        val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val cardPage = CardPage.cardTranslation(TestUser.getCard1Number().getNumber());
        cardPage.cardTranslation(amountAdded, TestUser.getCard1Number().getNumber());
        $(new ByText("Ваши карты")).should(visible);
    }
}