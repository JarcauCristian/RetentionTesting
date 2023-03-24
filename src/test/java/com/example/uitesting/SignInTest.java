package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInTest {
    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void signInFormWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        sleep(2000);
        $("input[id='kc-login']").click();

        assertEquals("https://retention-csb-test.biomed.ntua.gr/patients-monitoring", url());

    }
}
