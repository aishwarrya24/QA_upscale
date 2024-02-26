import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Amazon_Login {

    @Test
    public void verifyLogin() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();

            // Navigate to Amazon.com
            page.navigate("https://www.amazon.in");
            System.out.println("Page Title: " + page.title());

            // Wait for page load state
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            // Click on Sign-In Button
            ElementHandle signInButton = page.waitForSelector("#nav-link-accountList-nav-line-1");
            assertNotNull(signInButton, "Sign-in button not found.");
            signInButton.click();

            // Wait for the login page to load
            page.waitForLoadState();

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed: " + e.getMessage());
        }
    }
}
