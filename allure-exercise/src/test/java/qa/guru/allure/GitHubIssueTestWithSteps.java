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

@Feature("Work with issues by steps")
public class GitHubIssueTestWithSteps {
    private final static int ISSUE_NUMBER = 1;
    private final BasicSteps steps = new BasicSteps();

    @Before // <= this is JUnit notation
    //Adding listener of selenide before all tests execution
    public void initSelenideListener () {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }
    @Test
    @Story("Check if issues do exist with steps")
    @DisplayName("Allure Example - issue must exists in github repository - with steps")
    public void testIssueExists() {
        steps.openMainPage();
        steps.searchForRepository("cheshi-mantu/qa-guru");
        steps.openRepositoryIssues();
        steps.shouldSeeIssueWithID(ISSUE_NUMBER);
    }
}
