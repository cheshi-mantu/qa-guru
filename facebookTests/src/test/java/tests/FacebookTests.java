package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideWait;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.text;
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
}
