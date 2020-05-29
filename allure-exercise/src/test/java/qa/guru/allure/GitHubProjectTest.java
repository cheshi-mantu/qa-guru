package qa.guru.allure;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Work with Projects")
public class GitHubProjectTest {
        @Test
        @Story("Check if projects do exist")
        @DisplayName("Allure Example - check if created project exists")
        public void testCreatedProjectExists() {
            //assertEquals(true, false);
            assertTrue(false);
        }

        @Test
        @Story("Check if projects do exist")
        @DisplayName("Allure Example - check if default project exists")
        public void testDefaultProjectExists() {
                //assertEquals(true, false);
                assertTrue(true);
        }

}