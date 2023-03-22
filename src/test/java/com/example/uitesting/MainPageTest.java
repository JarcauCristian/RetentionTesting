package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

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

}
