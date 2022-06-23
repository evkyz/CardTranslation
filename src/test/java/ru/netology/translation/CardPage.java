package ru.netology.translation;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardPage {
    private SelenideElement yourCards = $x("//h1");
    private static ElementsCollection cards = $$x("//li[@class=\"list__item\"]");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public CardPage() {
        yourCards.shouldBe(visible);
    }

    public static CardTranslation cardTranslation(String numberCard) {
        cards.find(text(numberCard.substring(16, 19))).$$("[data-test-id='action-deposit']").first().click();
        return new CardTranslation();
    }

    private static int ExtractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public static int BalanceCard(String numberCard) {
        val text = cards.find(text(numberCard.substring(16, 19))).getText();
        return ExtractBalance(text);
    }
}
