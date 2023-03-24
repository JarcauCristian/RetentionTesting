package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void addPatientButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");
        sleep(1000);
        $("a[id*='toolbar']").click();
        sleep(1000);

        assertEquals("https://retention-csb-test.biomed.ntua.gr/addPatient", url());
    }

    @Test
    public void deviceMonitoringLinkWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");
        sleep(1000);

        $("a[routerlink='/notifications']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/notifications", url());

    }

    @Test
    public void clinicalStudiesLinkWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");
        sleep(1000);

        $("a[routerlink='/events-record']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/events-record", url());

    }

    @Test
    public void modelSelectionLinkWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");
        sleep(1000);

        $("a[routerlink='/model-selection']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/model-selection", url());

    }

    @Test
    public void searchFieldWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");
        sleep(1000);
        $("input[id='mat-input-0']").setValue("Accepted");
        sleep(1000);

        SelenideElement table = $("mat-table[class^='mat-table']");
        ElementsCollection rows = table.$$("mat-row");
        int numRows = rows.size();
        for (int i = 1; i <= numRows; i++)
        {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div > mat-table > mat-row:nth-of-type(" + String.valueOf(i) + ") > mat-cell:nth-of-type(3)").shouldHave(text("Accepted"));
        }
    }

    @Test
    public void addPatientFormWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/patients-monitoring");

        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        $("a[id*='toolbar']").click();
        sleep(1000);
        $("input[id='firstName']").setValue("John");
        $("input[id='lastName']").setValue("Harris");
        $("input[id='date']").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("input[id='address']").setValue("Elden Street 3A");
        $("input[id='zip']").setValue("400303");
        $("#kt_content_container > app-builder > div > div.card-body > div > div > form > div:nth-child(2) > div:nth-child(3) > mat-form-field").click();
        int id = 0;
        for (int i = 0; i < 20; i++) {
            if ($("mat-option[id='mat-option-" + String.valueOf(i) + "']").exists()) {
                id = i;
                break;
            }
        }
        $("mat-option[id='mat-option-" + String.valueOf(id) + "']").sendKeys(Keys.ENTER);
        $("input[id='telephone']").setValue("0775678970");
        $("input[id='mat-input-7']").setValue("john.harris@gmail.com");
        $("input[id='date1']").setValue(SetValueOptions.withDate(LocalDate.now()));
        $("#kt_content_container > app-builder > div > div.card-body > div > div > form > mat-form-field").click();
        id = 0;
        for (int i = 0; i < 20; i++) {
            if ($("mat-option[id='mat-option-" + String.valueOf(i) + "']").exists()) {
                id = i;
                break;
            }
        }
        $("mat-option[id='mat-option-" + String.valueOf(id) + "']").sendKeys(Keys.ENTER);
        $("input[id='mat-input-9']").setValue("caregiver1@gmail.com");

        sleep(1000);

        $("#kt_content_container > app-builder > div > div.card-footer > div.row > div > div > button.btn.btn-lg.btn-primary").click();
        sleep(1000);
        driver().switchTo().alert().accept();
        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/patients-monitoring", url());
    }

}
