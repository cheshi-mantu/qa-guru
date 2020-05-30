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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Feature("Work with issues")
public class GitHubIssueTest {
    private final static int ISSUE_NUMBER = 1;
    private final static String REPOSITORY = "cheshi-mantu/qa-guru";
    private final static String USERNAME = "cheshi-mantu";

    private GitHubClient github;


    @Before // <= this is JUnit notation
    //Adding listener of selenide before all tests execution
    public void initSelenideListener () {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }
    @Before
    public void initGitHubClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build();

        github = retrofit.create(GitHubClient.class);
    }
    @Test
    public void createIssue() throws Exception {

    }


    @Test
    @Story("Check if issues do exist")
    @DisplayName("Allure Example - issue must exists in github repository")
    public void testIssueExists() throws Exception {
        parameter("Issue number", ISSUE_NUMBER);
        parameter("Working with repository", REPOSITORY);
        parameter("user", USERNAME);

        final Issue issue = new Issue();
        issue.setTitle("test issue creation");
        issue.setBody("the very test body");
        Issue created = github.createIssue("cheshi-mantu", "qa-guru", issue).execute().body();
        System.out.println(created.getId());


        step("1. Open main page", () -> {
            open("https://github.com");
        });
        //step("2. Authentication as user");
        step("3. Open user's homepage with repo", ()-> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
            $(By.linkText(REPOSITORY)).shouldHave(Condition.visible);
            $(By.linkText(REPOSITORY)).click();
        });
        step("4. Open user's page with issues", ()-> {
            $(withText("Issues")).click();
        });
        step("5. Open check if we have issue with ID=" + created.getNumber(), ()-> {
            $(withText("#"+created.getNumber())).exists();
        });



    }
}
