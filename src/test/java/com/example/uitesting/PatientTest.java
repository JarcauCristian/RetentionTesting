package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void viewPatientButtonWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        for (int i = 1; i <= 10; i++) {
            String id = $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div > mat-table > mat-row:nth-of-type(" + i + ") > mat-cell:nth-of-type(1)").getText();
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div > mat-table > mat-row:nth-of-type(" + i + ") > mat-cell:nth-of-type(8) > button:nth-of-type(1) > span:nth-of-type(1) > mat-icon").click();
            sleep(1000);
            String url = "https://retention-csb-test.biomed.ntua.gr/builder;patientId=" + id;
            if (!url().equals(url)) {
                errors.add("View button " + i + " doesn't work!");
            }
            $("a[class='menu-link']").click();
        }


        assertEquals(new ArrayList<>(), errors);

    }

}
