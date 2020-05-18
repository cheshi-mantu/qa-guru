package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
//import pages.FacebookPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;

@Epic("QA.GURU automation course")
@Story("Facebook tests")
@Tag("facebook")
class FacebookTests extends TestBase {
// all stuff that starts with @ is from JUnit - platform for tests
    @Test
    @Description("Positive test with testid")
    void successfulLoginWithTestId() {
        Configuration.browser = "opera";
//        we'll use systemProperty "url" defined in helpers.Environment instead of using hardcoded string with web-site address
//        open("http://facebook.com");
        open(url);

        $(by("data-testid", "royal_email")).setValue("cheshi.mantu@gmail.com"); // Do not store private data in code!
//        $("#email").setValue("cheshi.mantu@gmail.com");
//        $(byId("email")).setValue("cheshi.mantu@gmail.com");
//        $(".inputtext.login_form_input_box").setValue("cheshi.mantu@gmail.com");
//        $(".login_form_input_box").setValue("cheshi.mantu@gmail.com");

        $(by("data-testid", "royal_pass")).setValue("j3qq4h7h2v2hch4");
        $(by("data-testid", "royal_login_button")).click();
//        $(byText("Вход")).click();

        $("html").shouldHave(text("Cheshi"));
//        $("html").shouldHave(text("Cheshi"), text("Mantu"));
    }

//    @Test
//    @Description("Test with PageObject")
//    void unsuccessfulLoginWithPageObject() {
//        Configuration.browser = "opera";
//        FacebookPage facebookPage = new FacebookPage();
//
//        open("http://facebook.com");
//
//        facebookPage.typeEmail("cheshi.mantu@gmail.com");
//        facebookPage.typePassword("testpassword#&!");
//        facebookPage.clickSubmit();
//        facebookPage.verifyLoggedInAsUser("Qa", "Guru");
//    }
}
