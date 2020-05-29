package qa.guru.allure;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GitHubProjectTest {
        @Test
        @DisplayName("Allure Example - project must exists in github repository")
        public void testProjectExists() {
            //assertEquals(true, false);
            assertTrue(false);
        }
}