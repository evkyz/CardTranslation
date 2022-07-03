package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;

public class CardPage {
    private ElementsCollection cards = $$x("//li[@class=\"list__item\"]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public CardTranslation translationCard(String numberCard) {
        cards.find(text(numberCard.substring(16, 19))).$$("[data-test-id='action-deposit']").first().click();
        return new CardTranslation();
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int balanceCard(String numberCard) {
        val text = cards.find(text(numberCard.substring(16, 19))).getText();
        return extractBalance(text);
    }
}
