package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.ArrayList;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void eventsTrueWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(16) > a").click();


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("Critical");

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++) {
            String text = $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i+1) + ") > td:nth-of-type(2)").getText();
            if (!text.equals("Critical")) {
                errors.add("Row invalid!");
            }
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void eventsFalseWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(16) > a").click();

        String dummyText = "fjdkajfkadk";
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(dummyText);

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");


        assertEquals("No data matching the filter \"" + dummyText + "\"", collection.get(0).getText());
    }

    @Test
    public void addEventsWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(16) > a").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div.tab-pane.active > div > app-special-events > mat-toolbar > button").click();
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");
        int sizeBefore = collection.size();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("10");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(3) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("6");

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(4) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(2)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(5) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(6) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(7) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(8) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(2)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(9) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("6");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(10) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("6");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(11) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("6");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(12) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("6");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(13) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("No");

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(2) > button:nth-of-type(2)").click();
        sleep(2000);
        int sizeAfter = collection.size();
        assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void editEventsWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(16) > a").click();

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");
        int initSize = collection.size();
        for (int i = 0; i < collection.size(); i++) {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i+1) + ") > td:nth-of-type(14) > button:nth-of-type(1) > span:nth-of-type(1) > mat-icon").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("10");
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-special-events > div:nth-of-type(2) > button:nth-of-type(2)").click();
            sleep(1000);
            String text = $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i+1) + ") > td:nth-of-type(2)").getText();
            if (!text.equals("10"))
            {
                errors.add("Row doesn't match!");
            }
        }
        sleep(2000);
        assertEquals(new ArrayList<>(), errors);
    }

    @Test
    public void deleteEventsWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(16) > a").click();

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");
        int initSize = collection.size();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(14) > button:nth-of-type(2) > span:nth-of-type(1) > mat-icon").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > delete > div > div > span > button:nth-of-type(1)").click();
        collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(13) > div > app-events > div > div > div > table > tbody tr");
        sleep(2000);
        if (initSize == 1) {
            assertEquals(initSize, collection.size());
        }
        else {
            assertEquals(initSize-1, collection.size());
        }
    }
}
