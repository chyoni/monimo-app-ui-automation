import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Slf4j
public class AppiumSampleTest {

    public static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("HVA1FG23");

        URL appiumServer = URI
                .create("http://0.0.0.0:4723")
                .toURL();
        driver = new AndroidDriver(appiumServer, options);
    }

    @Test
    public void test() {
        driver.findElement(AppiumBy.id("net.ib.android.smcard:id/btn_server")).click();
        String pageSource = driver.getPageSource();

        System.out.println("pageSource = " + pageSource);
        driver.quit();
    }
}
