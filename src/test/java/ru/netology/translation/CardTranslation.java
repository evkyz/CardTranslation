package ru.netology.translation;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CardTranslation {
    private SelenideElement amount = $x("//input[@value=\"\"]");
    private SelenideElement from = $x("//input[@placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement buttunTopUp = $x("//button[@data-test-id=\"action-transfer\"]");

    public CardPage cardTranslation(String deposit, String whereFrom) {
        amount.setValue(deposit);
        from.setValue(whereFrom);
        buttunTopUp.click();
        return new CardPage();
    }
}