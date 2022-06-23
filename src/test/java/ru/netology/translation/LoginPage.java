package ru.netology.translation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private SelenideElement login = $x("//input[@name=\"login\"]");
    private SelenideElement password = $x("//input[@name=\"password\"]");
    private SelenideElement button = $x("//span[@class=\"button__content\"]");

    public LoginPage() {
        login.shouldBe(Condition.visible);
    }

    public VerificationPage validLogin(TestUser.AuthInfo authInfo) {
        login.setValue(authInfo.getLogin());
        password.setValue(authInfo.getPassword());
        button.click();
        return new VerificationPage();
    }
}

