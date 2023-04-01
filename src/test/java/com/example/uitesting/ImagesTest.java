package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImagesTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void imagesTrueWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("423");

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        for (SelenideElement element : collection) {
            String text = element.getText();
            if (!text.split(" ")[3].equals("423")) {
                errors.add("Row invalid!");
            }
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void imagesFalseWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();

        String dummyText = "fjdkajfkadk";
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(dummyText);

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");


        assertEquals("No data matching the filter \"" + dummyText + "\"", collection.get(0).getText());
    }

    @Test
    public void imagesEditWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();

        List<String> texts = Arrays.asList("423 423 423 423 423 Dec 12, 2022 visibility\nedit\ndelete");
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++)
        {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody > tr:nth-child(" + i+1 + ") > td:nth-of-type(7) > button:nth-of-type(2) > span:nth-of-type(1) > mat-icon").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(2) > button:nth-of-type(2)").click();
            String txt = collection.get(i).getText();
            System.out.println(txt);
            if (!texts.get(i).equals(collection.get(i).getText()))
            {
                errors.add("Edit doesn't work!");
            }
            sleep(2000);
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void addImageWorks() throws AWTException {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > mat-toolbar > button").click();
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        int sizeBefore = collection.size();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > div > button").click();
        SelenideElement element = $("#mat-dialog-0 > app-images-dialog > div.mat-dialog-content > form > div > input");
        element.sendKeys("E:\\Informatie.docx");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(3) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(4) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(5) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("423");
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-images-dialog > div:nth-of-type(2) > button:nth-of-type(2)").click();
        sleep(2000);
        int sizeAfter = collection.size();
        assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void viewButtonWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++) {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i+1) + ") > td:nth-of-type(7) > button:nth-of-type(1) > span:nth-of-type(1) > mat-icon").click();
            if (!$("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > hello > div > div:nth-of-type(1) > img").exists())
            {
                errors.add("View Button doesn't work!");
            }
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > hello > div > div:nth-of-type(2) > span > button").click();
        }
    }

    @Test
    public void deleteButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(5) > a").click();

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++) {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i+1) + ") > td:nth-of-type(7) > button:nth-of-type(3) > span:nth-of-type(1) > mat-icon").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > delete > div > div > span > button:nth-of-type(1)").click();
            collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(28) > div > app-images > div > div > div > table > tbody tr");
        }

        assertEquals(0, collection.size());
    }
}
