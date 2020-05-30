package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasicSteps {


    public void openMainPage(){
        open("https://github.com");
    }
    @Step("3. Open user's homepage with repo")
    public void searchForRepository(String name){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
        $(By.linkText(name)).shouldHave(Condition.visible);
        $(By.linkText(name)).click();
    }
    @Step("4. Open user's page with issues")
    public void openRepositoryIssues(){
        $(withText("Issues")).click();

    }
    @Step("5. Open check if we have issue with ID=")
    public void shouldSeeIssueWithID(int number){
        $(withText("#"+number)).exists();
    }
}
