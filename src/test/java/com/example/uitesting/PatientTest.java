package com.example.uitesting;

import com.beust.ah.A;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import com.codeborne.selenide.commands.SetValue;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void patientDataButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(1) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(1)").getAttribute("class"));
    }

    @Test
    public void baselineButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(2) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(2)").getAttribute("class"));
    }

    @Test
    public void devicesButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(3) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(4)").getAttribute("class"));
    }

    @Test
    public void overviewButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(1) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(5)").getAttribute("class"));
    }

    @Test
    public void visitsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(2) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(6)").getAttribute("class"));
    }

    @Test
    public void medicationButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(3) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(23)").getAttribute("class"));
    }

    @Test
    public void personalizedAnalyticsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(4) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(27)").getAttribute("class"));
    }

    @Test
    public void imagesButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(5) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(28)").getAttribute("class"));
    }

    @Test
    public void clinicalDataButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(6) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(24)").getAttribute("class"));
    }

    @Test
    public void ecgButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(7) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(25)").getAttribute("class"));
    }

    @Test
    public void labsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(8) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(8)").getAttribute("class"));
    }

    @Test
    public void echocardiographyButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(9) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(26)").getAttribute("class"));
    }

    @Test
    public void mwtButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(10) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(22)").getAttribute("class"));
    }

    @Test
    public void vadButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(11) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(9)").getAttribute("class"));
    }

    @Test
    public void cardioPulmonaryButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(12) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(21)").getAttribute("class"));
    }

    @Test
    public void careGiverButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(13) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(10)").getAttribute("class"));
    }

    @Test
    public void rwdSensorsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(14) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(11)").getAttribute("class"));
    }

    @Test
    public void otherDataButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(15) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(12)").getAttribute("class"));
    }

    @Test
    public void eventsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(16) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(13)").getAttribute("class"));
    }

    @Test
    public void specialEventsButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(17) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(14)").getAttribute("class"));
    }

    @Test
    public void symptomsQuestionnaireButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(18) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(15)").getAttribute("class"));
    }

    @Test
    public void uestionnaireButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("button[id='monitoring']").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(19) > a").click();

        assertEquals("tab-pane active", $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div:nth-child(7)").getAttribute("class"));
    }

    @Test
    public void patientDataWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(1) > a").click();

        int counter = 0;

        ElementsCollection elements = $$("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div.tab-pane.active > form");

        for (SelenideElement element : elements)
        {
            String text = "Edit\n" +
                    "Name:\n" +
                    "DM-LVAD-RPMUC-01\n" +
                    "Sex:\n" +
                    "Male\n" +
                    "Ethnicity:\n" +
                    "European\n" +
                    "Country of residence:\n" +
                    "Germany\n" +
                    "Date of Birth:\n" +
                    "Oct 11, 1955\n" +
                    "Marital Status:\n" +
                    "living with a partner/spouse\n" +
                    "Email:\n" +
                    "string\n" +
                    "Home Address:\n" +
                    "string\n" +
                    "Zip number:\n" +
                    "string\n" +
                    "Phone number:\n" +
                    "string\n" +
                    "Consent Date:\n" +
                    "Mar 20, 2023\n" +
                    "Recruitment status:\n" +
                    "Accepted\n" +
                    "Height (cm):\n" +
                    "Employment Status:\n" +
                    "ABO Blood group:\n" +
                    "Highest Level of Education:";
            if (!text.equals(element.getText()))
            {
                counter++;
            }
        }
        assertEquals(0, counter);
    }

    @Test
    public void patientEditButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(1) > a").click();

        $("a[style='margin-bottom: 3%;']").click();
        $("input[id='mat-input-30']").setValue("john@gmail.com");
        $("a[style='margin-bottom: 3%;']").click();
        assertEquals("john@gmail.com", $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(1) > form > div:nth-of-type(3) > div:nth-of-type(3) > h5").getText());
    }

    @Test
    public void baselineSubmitButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(2) > a").click();

        $("a[style='margin-bottom: 3%;']").click();
        $("input[id='mat-input-30']").setValue("john@gmail.com");
        $("a[style='margin-bottom: 3%;']").click();
        assertEquals("john@gmail.com", $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(1) > form > div:nth-of-type(3) > div:nth-of-type(3) > h5").getText());
    }

    @Test
    public void assignDevicesButtonWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(3) > a").click();

        $("mat-label[style='padding-bottom: 3%;'] a[id*='toolbar']").click();
        $("input[id='mat-input-28']").setValue("3883993938738");
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(2) > div > form > div > div:nth-of-type(2) > mat-form-field > div > div > div").click();
        $("#kt_body > div > div:nth-child(2) > div > div > div > mat-option:nth-child(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(2) > div > form > div > div:nth-of-type(3) > mat-form-field >div > div > div").click();
        $("#kt_body > div > div:nth-child(2) > div > div > div > mat-option:nth-child(1)").sendKeys(Keys.SPACE);
        $("button[class='btn btn-primary btn-color-muted']").click();
        sleep(2000);
        assertEquals("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67", url());
    }

    @Test
    public void baselineWorks() {

        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-header.card-header-stretch.overflow-auto > ul > li:nth-child(2) > a").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(1) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(1) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(1) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(2) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(2) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(2) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("input[data-placeholder='0-150']").setValue("140");
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(3) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(3) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(4) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(4) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(4) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(4) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(5) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(6) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(1) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(6) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(5) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(5) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(6) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(6) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(7) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(7) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(9) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(10) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(11) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(12) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(13) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(14) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(15) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(15) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(16) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);

        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(19) > div > mat-form-field > div > div:nth-of-type(1) > div > mat-select > div > div:nth-of-type(2)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > div > div > mat-option:nth-of-type(1)").sendKeys(Keys.SPACE);


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(7) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(8) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(12) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(15) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(17) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(16) > div:nth-of-type(2) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(17) > div:nth-of-type(3) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(8) > div:nth-of-type(1) > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("150");


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(8) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(9) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(9) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(10) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(18) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(18) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(18) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(17) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(16) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(14) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        executeJavaScript("window.scrollTo(document.body.scrollHeight, 300)");
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(14) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(13) > div:nth-of-type(2) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(13) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(13) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(2)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(12) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(11) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(11) > div:nth-of-type(1) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > app-baseline > mat-horizontal-stepper > div > div:nth-of-type(2) > div > form > div:nth-of-type(10) > div:nth-of-type(3) > section > mat-radio-group > mat-radio-button:nth-of-type(2) > label > span:nth-of-type(1) > span:nth-of-type(1)").click();
        $("button[class='btn btn-sm cursor-pointer btn-primary']").click();
        driver().switchTo().alert().accept();
        sleep(2000);
    }

    @Test
    public void visitsTrueWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(2) > a").click();


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("Live");

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody tr");
        for (SelenideElement element : collection) {
            String text = element.getText();
            if (!text.split(" ")[3].equals("Live")) {
                errors.add("Row invalid!");
            }
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void visitsFalseWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(2) > a").click();

        String dummyText = "fjdkajfkadk";
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(dummyText);

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody tr");


        assertEquals("No data matching the filter \"" + dummyText + "\"", collection.get(0).getText());
    }

    @Test
    public void visitsEditWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(2) > a").click();

        List<String> texts = Arrays.asList("Jul 2, 2000 Call 254805 edit\ndelete", "Jul 2, 2000 Call 254807 edit\ndelete");
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++)
        {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody > tr:nth-of-type(" + String.valueOf(i + 1) + ") > td:nth-of-type(4) > button:nth-of-type(1) > span:nth-of-type(1) > mat-icon").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3) > mat-select > div > div:nth-of-type(1) > span").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1) > span").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(2) > button:nth-of-type(2)").click();
            if (!texts.get(i).equals(collection.get(i).getText()))
            {
                errors.add("Edit doesn't work!");
            }
            sleep(2000);
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void addVisitsWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(2) > a").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div.tab-pane.active > div > app-vn > mat-toolbar > button").click();
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody tr");
        int sizeBefore = collection.size();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3) > mat-select > div > div:nth-of-type(1) > span").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(2) > button:nth-of-type(2)").click();
        sleep(2000);
        int sizeAfter = collection.size();
        assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void medicationTrueWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(3) > a").click();


        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue("Losartan");

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++) {
            String text = collection.get(i).getText();
            if (!text.split(" ")[2].equals("Losartan"))
            {
                errors.add("Row doesn't match!");
            }
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void medicationFalseWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(3) > a").click();

        String dummyText = "fjdkajfkadk";
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > mat-form-field > div > div:nth-of-type(1) > div > input").setValue(dummyText);

        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > div > table > tbody tr");


        assertEquals("No data matching the filter \"" + dummyText + "\"", collection.get(0).getText());
    }

    @Test
    public void medicationEditWorks() {
        ArrayList<String> errors = new ArrayList<>();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(3) > a").click();

        List<String> texts = Arrays.asList("254809 ARBs Losartan 46 gr Mar 29, 2023 edit\ndelete");
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > div > table > tbody tr");
        for (int i = 0; i < collection.size(); i++)
        {
            $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(23) > app-medication-new > div > div > div > table > tbody > tr:nth-child(" + i+1 + ") > td:nth-of-type(8) > button:nth-of-type(1) > span:nth-of-type(1) > mat-icon").click();
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog > div:nth-of-type(1) > form > mat-form-field:nth-of-type(3) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue("46");
            $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog > div:nth-of-type(2) > button:nth-of-type(2)").click();
            sleep(2000);

            if (!texts.get(i).equals(collection.get(i).getText()))
            {
                errors.add("Edit doesn't work!");
            }
            sleep(2000);
        }

        assertEquals(new ArrayList<String>(), errors);
    }

    @Test
    public void addMedicationWorks() {
        open("https://retention-csb-test.biomed.ntua.gr/builder;patientId=67");
        $("input[id='username']").setValue("dev");
        $("input[id='password']").setValue("qwer1234");
        $("input[id='kc-login']").click();
        sleep(4000);
        $("button[id='monitoring']").click();
        $("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(1) > ul > li:nth-of-type(3) > a").click();

        $("#kt_content_container > app-builder > div:nth-child(3) > div.card-body > div > div.tab-pane.active > div > app-vn > mat-toolbar > button").click();
        ElementsCollection collection = $$("html > body > app-layout > div > div > div > div > div > app-content > app-builder > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(6) > div > app-vn > div > div > div > table > tbody tr");
        int sizeBefore = collection.size();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(3) > input").setValue(SetValueOptions.withDate(LocalDate.of(2000, 07, 02)));
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(1) > form > mat-form-field:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(3) > mat-select > div > div:nth-of-type(1) > span").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(4) > div > div > div > mat-option:nth-of-type(1)").click();
        $("html > body > div:nth-of-type(3) > div:nth-of-type(2) > div > mat-dialog-container > app-dialog-vn > div:nth-of-type(2) > button:nth-of-type(2)").click();
        sleep(2000);
        int sizeAfter = collection.size();
        assertEquals(sizeBefore+1, sizeAfter);
    }
}
