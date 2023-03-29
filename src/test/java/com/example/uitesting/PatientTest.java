package com.example.uitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
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

    @Test
    public void patientBasicInformationWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);

        String text = "ID: 67\n" +
                "Patient: DM-LVAD-RPMUC-01\n" +
                "Recruitment status: Accepted";
        if (!text.equals($("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(1) > div:nth-of-type(2) > div:nth-of-type(1) > h3").getText()))
        {
            errors.add("First h3 doesn't match!");
        }

        text = "Phone: string\n" +
            "Birthdate: Oct 11, 1955\n" +
            "Patient Group: LVAD";

        if (!text.equals($("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(1) > div:nth-of-type(2) > div:nth-of-type(2) > h3").getText()))
        {
            errors.add("Second h3 doesn't match!");
        }
        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void basicPatientInfoButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);

        $("button[id='basic']").click();

        ElementsCollection list = $$("ul[class$='flex-nowrap'] li");

        int error_counter = 0;
        for (SelenideElement element : list)
        {
            String text = element.getText();
            if (!text.equals("Patient Data") && !text.equals("Baseline") && !text.equals("Devices"))
            {
                error_counter++;
            }
        }

        assertEquals(0, error_counter);
    }

    @Test
    public void patientMonitoringButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);

        $("button[id='monitoring']").click();

        ElementsCollection list = $$("ul[class$='flex-nowrap'] li");

        int error_counter = 0;
        for (SelenideElement element : list)
        {
            String text = element.getText();
            if (!text.equals("Overview") && !text.equals("Visits") && !text.equals("Medication") && !text.equals("Personalized Analytics") && !text.equals("Images") && !text.equals("ClinicalData") && !text.equals("ECG") && !text.equals("Labs") && !text.equals("Echocardiography") && !text.equals("6MWT") && !text.equals("VAD") && !text.equals("CardioPulmonary") && !text.equals("Caregiver") && !text.equals("RWDSensors") && !text.equals("OtherData") && !text.equals("Events") && !text.equals("SpecialEvents") && !text.equals("SymptomsQuestionnaire") && !text.equals("Questionnaire"))
            {
                error_counter++;
            }
        }

        assertEquals(0, error_counter);
    }

    @Test
    public void notificationsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);

        $("button[id='Notification']").click();

        ElementsCollection list = $$("ul[class$='flex-nowrap'] li");

        int error_counter = 0;
        for (SelenideElement element : list)
        {
            String text = element.getText();
            if (!text.equals("Notifications"))
            {
                error_counter++;
            }
        }

        assertEquals(0, error_counter);
    }
}
