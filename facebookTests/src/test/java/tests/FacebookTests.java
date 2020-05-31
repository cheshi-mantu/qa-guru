package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideWait;
import helpers.fileReadHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;


@Epic("QA.GURU QA automation course")
@Story("Facebook tests")
@Tag("facebook_test")
class FacebookTests extends TestBase {
    private String finalEmail ="";
    private String finalPassword = "";
    @BeforeEach
    void setFinalEmail() {
        if (helpers.Environment.email == "empty_string_returned") {
            final fileReadHelper txtEmail = new fileReadHelper();
            finalEmail = txtEmail.getStringFromFile("D:\\code\\qa-guru\\facebookTests\\src\\test\\java\\tests\\facebookname.secret");
        } else {
            finalEmail = email;
        }
    }
    @BeforeEach
    void setFinalPassword(){
        if (password == "empty_string_returned") {
            final fileReadHelper txtPassword = new fileReadHelper();
            finalPassword = txtPassword.getStringFromFile("D:\\code\\qa-guru\\facebookTests\\src\\test\\java\\tests\\facebookpassword.secret");
        } else {
            finalPassword = password;
        }
    }

// all stuff that starts with @ is from JUnit - platform for tests
//    how to start the test from command line:
//    gradle facebook_tests -Durl=http://facebook.com -Demail=cheshi.mantu@gmail.com -Dpassword=”fuckoff”
//    gradle - builder which runs all the stuff
//    facebook_tests is task from build.gradle it will select needed tasks by tag "@Test" used by JUnit and tag @Tag("facebook"), see above
    @Test
    @Description("Positive test with data-testid")
    void successfulLoginWithTestId() {
        System.out.println("Email: " + finalEmail);
        System.out.println("Password: " + finalPassword);

        Configuration.browser = "opera";
        Configuration.timeout = 6;
//        we'll use systemProperty "url" defined in helpers.Environment instead of using hardcoded string with web-site address
//        open("http://facebook.com");
        open(url);
//        we'll use systemProperty "email" defined in helpers.Environment instead of using hardcoded string with email address

        $(by("data-testid", "royal_email")).setValue(finalEmail); // Do not store private data in code!
//        $("#email").setValue(email);
//        $(byId("email")).setValue(email);
//        $(".inputtext.login_form_input_box").setValue(email);
//        $(".login_form_input_box").setValue(email);
//        we'll use systemProperty "password" defined in helpers.Environment instead of using hardcoded string with password
        $(by("data-testid", "royal_pass")).setValue(finalPassword);
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

        facebookPage.typeEmail(finalEmail);

        facebookPage.typePassword(finalPassword);

        facebookPage.clickSubmit();

        facebookPage.verifyLoggedInAsUser("Cheshi");
    }
    @Test
    @Story("Facebook profile update test")
    @Description("Open page, click on profile, click on Edit profile, update some item, and check")
    void userProfileUpdate() {
        Configuration.browser = "opera";
//        Configuration.browser = "edge";
//        Configuration.browser = "firefox";
//        Configuration.browser = "opera";
//        Configuration.browser = "opera";
        Configuration.startMaximized = true;
        Configuration.timeout = 6;

        //Configuration.browserSize = "1920x1080";
        FacebookPage facebookPage = new FacebookPage();
            step ("Open facebook page via http link", () -> {
            open(url);
            facebookPage.typeEmail(finalEmail);
            facebookPage.typePassword(finalPassword);
            facebookPage.clickSubmit();
            facebookPage.verifyLoggedInAsUser("Cheshi");
        }
        );

            // now we start to update user's profile
            step("Enter the user profile", () -> {
                $(byAttribute("title", "Profile")).click(); // works
                //$(byText("Cheshi")).click(); // works
                //$(withText("Edit Profile")).should(exist); // not always works
                $(byText("Edit Profile")).should(exist);
//                $(byAttribute("ajaxify","/profile/edit/public/show/")).should(exist); //works
            }
            );
            step("Click Edit Profile", ()->{
                $(byAttribute("ajaxify","/profile/edit/public/show/")).click(); // works
                $(withText("Edit Profile")).should(exist);
                //$(byText("Edit Profile")).click(); // works
            });
            step("Edit about info, Life Events link must be available on the page", ()->{
                $(byText("Edit your about info")).click();
                $("#medley_header_about").should(exist);
                $(byLinkText("About")).should(exist);
                $(byLinkText("Life Events")).should(exist);
            });
            step("Go to Life Events and check if 'Add a life event' exists", ()->{
                $(byLinkText("Life Events")).click();
                $("html").shouldHave(text("Add a life event"));

            });
            step("Click on Add a life event", ()->{
                $(byText("Add a life event")).click();
                $(byText("Life Events")).should(exist);
                $(withText("Relationship")).should(exist);
            });
//            step("", ()->{});
//            step("", ()->{});
//            step("", ()->{});
//            step("", ()->{});
//            step("", ()->{});






//        $(byText("Relationship")).click();
//        $(byText("New Relationship")).click();
//        $(withText("Say something")).click();
////      $(withText("Say something")).setValue("00223311");
////      $(byTagName("span")).$(byAttribute("data-text", "true")).setValue("Мы покакунькали");
//        $(getFocusedElement()).setValue("112233445566");
//        $(byText("Share")).click();
//        sleep(1000);
//        $(byText("Timeline")).shouldBe(visible);
//        $(byText("Timeline")).click();
//        $(byTagName("div")).$(byAttribute("role", "feed")).shouldHave(text("112233445566"));
    }

}
