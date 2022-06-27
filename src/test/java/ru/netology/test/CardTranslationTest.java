package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.TestUser;
import ru.netology.page.CardPage;
import ru.netology.page.CardTranslation;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTranslationTest {
    int overBalance;

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
        //$(new ByText("Ваши карты")).should(visible);
    }

    @Test
    void testOverBalance() {
        //val balanceCard1 = CardPage.BalanceCard(TestUser.getCard1Number().getNumber());
        val balanceCard2 = CardPage.BalanceCard(TestUser.getCard2Number().getNumber());
        overBalance = balanceCard2 + 1000;
        if (overBalance > balanceCard2) {
            CardTranslation.ErrorTranslation();
        }
    }
}