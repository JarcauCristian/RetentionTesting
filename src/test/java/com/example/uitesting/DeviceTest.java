package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {
    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void createDeviceWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/notifications");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        $("#kt_toolbar_primary_button").click();
        sleep(1000);

        assertEquals("https://retention-csb-test.biomed.ntua.gr/addDevice", url());
    }

    @Test
    public void addDeviceWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/addDevice");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        $("div[id$='content'] > div > app-content > app-builder > div:nth-child(2) > div > form > div > div > mat-form-field > div > div:nth-child(1) > div > input").setValue("357631050052050");
        $("div[id$='content'] > div > app-content > app-builder > div:nth-child(2) > div > form > div > div:nth-child(2) > mat-form-field > div > div > div").click();
        int id = 0;
        for (int i = 0; i < 200; i++) {
            if ($("mat-option[id='mat-option-" + String.valueOf(i) + "']").exists()) {
                id = i;
                break;
            }
        }
        $("mat-option[id='mat-option-" + String.valueOf(id) + "']").sendKeys(Keys.ENTER);

        $("div[id$='content'] > div > app-content > app-builder > div:nth-child(2) > div > form > div > div:nth-child(3) > mat-form-field > div > div > div").click();
        id = 0;
        for (int i = 0; i < 200; i++) {
            if ($("mat-option[id='mat-option-" + String.valueOf(i) + "']").exists()) {
                id = i;
                break;
            }
        }
        $("mat-option[id='mat-option-" + String.valueOf(id) + "']").sendKeys(Keys.ENTER);
        $("button[class='btn btn-primary btn-color-muted']").click();
        sleep(2000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/notifications", url());
    }
}
