package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CardTranslation {
    private SelenideElement yourCards = $x("//h1");
    private SelenideElement translationNotPossible = $x("");
    private SelenideElement amount = $x("//input[@value=\"\"]");
    private SelenideElement from = $x("//input[@placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement buttunTopUp = $x("//button[@data-test-id=\"action-transfer\"]");

    public CardPage translationCard(String deposit, String whereFrom) {
        amount.setValue(deposit);
        from.setValue(whereFrom);
        buttunTopUp.click();
        return new CardPage();
    }
    public CardTranslation() {
        yourCards.shouldBe(visible);
    }

    public void errorTranslation() {
        translationNotPossible.shouldBe(visible);
    }
}