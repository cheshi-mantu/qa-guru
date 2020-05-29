package qa.guru.allure;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Work with issues")
public class GitHubIssuePlainTest {
    private final static int ISSUE_NUMBER = 1;

    @Before // <= this is JUnit notation
    //Adding listener of selenide before all tests execution
    public void initSelenideListener () {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }
    @Test
    @Story("Check if issues do exist")
    @DisplayName("Allure Example - issue must exists in github repository")
    public void testIssueExists() {
        step("1. Open main page", () -> {
            open("https://github.com");
        });
        //step("2. Authentication as user");
        step("3. Open user's homepage with repo", ()-> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("cheshi-mantu/qa-guru");
            $(".header-search-input").submit();
            $(By.linkText("cheshi-mantu/qa-guru")).shouldHave(Condition.visible);
            $(By.linkText("cheshi-mantu/qa-guru")).click();
        });
        step("4. Open user's page with issues", ()-> {
            $(withText("Issues")).click();
        });
        step("5. Open check if we have issue with ID=" + ISSUE_NUMBER, ()-> {
            $(withText("#"+ISSUE_NUMBER)).exists();
        });



    }
}
