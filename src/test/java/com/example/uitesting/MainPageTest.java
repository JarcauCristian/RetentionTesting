package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.Date;

import static com.codeborne.selenide.Condition.*;
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

    @BeforeEach
    public void setUp() {
        open("https://retention-csb-test.biomed.ntua.gr/");
    }

    @Test
    public void signInFormWorks() {
        // mainPage.searchButton.click();
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/patients-monitoring", url());

    }

    @Test
    public void addPatientButtonWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        $("a[id*='toolbar']").click();
        sleep(1000);

        assertEquals("https://retention-csb-test.biomed.ntua.gr/addPatient", url());
    }

    @Test
    public void patientsMonitoringLinkWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);

        $("a[class$='active']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/patients-monitoring", url());

    }

    @Test
    public void deviceMonitoringLinkWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);

        $("a[routerlink='/notifications']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/notifications", url());

    }

    @Test
    public void clinicalStudiesLinkWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);

        $("a[routerlink='/events-record']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/events-record", url());

    }

    @Test
    public void modelSelectionLinkWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);

        $("a[routerlink='/model-selection']").click();

        sleep(1000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/model-selection", url());

    }

    @Test
    public void searchFieldWorks() {
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
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
        String setDateScript = "arguments[0].value = arguments[1]";

        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(1000);
        $("a[id*='toolbar']").click();
        sleep(1000);
        $("input[id='firstName']").setValue("John");
        $("input[id='lastName']").setValue("Harris");
        SelenideElement inputElement = $("input[type='date']");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jsExecutor.executeScript(setDateScript, inputElement, "02-Jul-2000");
        $("input[id='address']").setValue("Elden Street 3A");
        $("input[id='zip']").setValue("400303");
        $("#kt_content_container > app-builder > div > div.card-body > div > div > form > div:nth-child(2) > div:nth-child(3) > mat-form-field").click();
        $("mat-option[id='mat-option-01'] span[class='mat-option-text']").sendKeys(Keys.ENTER);
        $("input[id='telephone']").setValue("0775678970");
        $("input[id='mat-input-7']").setValue("john.harris@gmail.com");
        inputElement = $("input[type='date']");
        jsExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jsExecutor.executeScript(setDateScript, inputElement, "23-Mar-2023");
        $("div[id='mat-select-value-5']").setValue("ccmname1");
        $("input[id='mat-input-9']").setValue("caregiver1@gmail.com");

        $("button[class='btn btn-lg btn-primary me-3']").click();
        sleep(1000);

        assertEquals("https://retention-csb-test.biomed.ntua.gr/patients-monitoring", url());
    }

}
