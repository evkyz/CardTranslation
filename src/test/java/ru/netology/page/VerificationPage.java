package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.TestUser;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement code = $x("//input[@name=\"code\"]");
    private SelenideElement button = $x("//span[@class=\"button__content\"]");

    public VerificationPage() {
        code.shouldBe(Condition.visible);
    }

    public CardPage validCode(TestUser.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        button.click();
        return new CardPage();
    }
}
