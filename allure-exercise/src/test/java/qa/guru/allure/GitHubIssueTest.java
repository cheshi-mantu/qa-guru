package qa.guru.allure;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Work with issues")
public class GitHubIssueTest {
    @Test
    @Story("Check if issues do exist")
    @DisplayName("Allure Example - issue must exists in github repository")
    public void testIssueExists() {
        step("1. Open main page");
        step("2. Authentication as user");
        step("3. Open user's homepage with repo");
        step("4. Open user's page with issues");
        step("5. Open check if issue with ID=12 exists");


    }
}
