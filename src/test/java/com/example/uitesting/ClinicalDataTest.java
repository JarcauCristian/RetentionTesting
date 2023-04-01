package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.ArrayList;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClinicalDataTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void clinicalDataTrueWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(6) > a").click();


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("at rest");

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody tr");
        for (SelenideElement element : collection) {
            String text = element.getText();
            if (!text.split(" ")[4].equals("at") && !text.split(" ")[5].equals("rest")) {
                errors.add("Row invalid!");
            }
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void clinicalDataFalseWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");

        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(6) > a").click();

        String dummyText = "fjdkajfkadk";
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(dummyText);

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody tr");


        assertEquals("No data matching the filter \"" + dummyText + "\"", collection.get(0).getText());
    }

    @Test
    public void addClinicalDataWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(6) > a").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div.tab-pane.active > app-clinicaldata > mat-toolbar > button").click();
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody tr");
        int sizeBefore = collection.size();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(3) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(1) > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(2) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(3) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(4) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(4) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(5) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(5) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(5) > div > div:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(7) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(8) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-radio-group:nth-of-type(9) > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(6) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("59");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(7) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("30");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(8) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("35");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(9) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("70");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(10) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("70");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(1) > form > mat-form-field:nth-of-type(11) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("40");

        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-clinical > div:nth-of-type(2) > button:nth-of-type(2)").click();
        sleep(2000);
        int sizeAfter = collection.size();
        assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void deleteClinicalDataWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(6) > a").click();

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody tr");
        int initSize = collection.size();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > button > span:nth-of-type(1) > mat-icon").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > delete > div > div > span > button:nth-of-type(1)").click();
        collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(24) > app-clinicaldata > div > div > section > table > tbody tr");
        sleep(2000);
        if (initSize == 1) {
            assertEquals(initSize, collection.size());
        }
        else {
            assertEquals(initSize-1, collection.size());
        }
    }
}
