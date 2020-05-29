package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideWait;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;


@Epic("QA.GURU automation course")
@Story("Facebook tests")
@Tag("facebook")
class FacebookTests extends TestBase {
// all stuff that starts with @ is from JUnit - platform for tests
//    how to start the test from command line:
//    gradle facebook_tests -Durl=http://facebook.com -Demail=cheshi.mantu@gmail.com -Dpassword=”fuckoff”
//    gradle - builder which runs all the stuff
//    facebook_tests is task from build.gradle it will select needed tasks by tag "@Test" used by JUnit and tag @Tag("facebook"), see above
    @Test
    @Description("Positive test with data-testid")
    void successfulLoginWithTestId() {
        Configuration.browser = "opera";
//        we'll use systemProperty "url" defined in helpers.Environment instead of using hardcoded string with web-site address
//        open("http://facebook.com");
        open(url);
//        we'll use systemProperty "email" defined in helpers.Environment instead of using hardcoded string with email address
        $(by("data-testid", "royal_email")).setValue(email); // Do not store private data in code!
//        $("#email").setValue(email);
//        $(byId("email")).setValue(email);
//        $(".inputtext.login_form_input_box").setValue(email);
//        $(".login_form_input_box").setValue(email);
//        we'll use systemProperty "password" defined in helpers.Environment instead of using hardcoded string with password
        $(by("data-testid", "royal_pass")).setValue(password);
        $(by("data-testid", "royal_login_button")).click();
//        $(byText("Вход")).click();

        $(by("data-testid", "left_nav_item_Cheshi Mantu")).shouldHave(text("Cheshi Mantu"));
//      $("html").shouldHave(text("Cheshi"), text("Mantu"));
    }

    @Test
    @Description("Successfull Facebook Test with PageObject and Env")
    void successfulLoginWithPageObject() {
        Configuration.browser = "opera";
        FacebookPage facebookPage = new FacebookPage();

        open(url);

        facebookPage.typeEmail(email);

        facebookPage.typePassword(password);

        facebookPage.clickSubmit();

        facebookPage.verifyLoggedInAsUser("Cheshi");
    }
    @Test
    @Description("Test for user profile update")
    void userProfileUpdate() {
        Configuration.browser = "opera";
//        Configuration.browser = "edge";
//        Configuration.browser = "firefox";
//        Configuration.browser = "opera";
//        Configuration.browser = "opera";
        Configuration.startMaximized = true;
        //Configuration.browserSize = "1920x1080";
        FacebookPage facebookPage = new FacebookPage();
        open(url);
        facebookPage.typeEmail(email);
        facebookPage.typePassword(password);
        facebookPage.clickSubmit();
        facebookPage.verifyLoggedInAsUser("Cheshi");
        // now we start to update user's profile
        $(byText("Cheshi")).click();
        $(byText("Edit Profile")).should(exist);
        $(byText("Edit Profile")).click();
//        $("h3").shouldHave(text("Edit Profile"));
        $(byText("Edit your about info")).click();
        $(byText("Life Events")).click();
        $("html").shouldHave(text("Add a life event"));
        $(byText("Add a life event")).click();
        $(withText("Relationship")).shouldBe(visible);
        $(byText("Relationship")).click();
        $(byText("New Relationship")).click();
        $(withText("Say something")).click();
//      $(withText("Say something")).setValue("00223311");
//      $(byTagName("span")).$(byAttribute("data-text", "true")).setValue("Мы покакунькали");
        $(getFocusedElement()).setValue("112233445566");
        $(byText("Share")).click();
        sleep(1000);
        $(byText("Timeline")).shouldBe(visible);
        $(byText("Timeline")).click();
        $(byTagName("div")).$(byAttribute("role", "feed")).shouldHave(text("112233445566"));
    }

}
