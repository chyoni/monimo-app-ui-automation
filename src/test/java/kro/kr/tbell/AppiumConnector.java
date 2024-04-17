package kro.kr.tbell;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

@Slf4j
public class AppiumConnector {
    private static AndroidDriver androidDriver;

    private static final Yaml yaml = new Yaml();

    private AppiumConnector() {}

    private static AndroidDriver getAndroidDriver() {
        if (androidDriver == null) {
            Map<String, Object> env = initializeYaml();

            String udid = (String) env.get("udid");
            String url = (String) env.get("url");

            try {
                URL appiumServer = URI.create(url).toURL();
                UiAutomator2Options options = new UiAutomator2Options().setUdid(udid);

                androidDriver = new AndroidDriver(appiumServer, options);
            } catch (MalformedURLException e) {
                log.error("[getAndroidDriver]: MalformedURLException", e);
                throw new RuntimeException(e);
            }
        }
        return androidDriver;
    }

    private static Map<String, Object> initializeYaml() {
        InputStream inputStream = AppiumConnector.class
                .getClassLoader()
                .getResourceAsStream("env.yaml");

        return yaml.load(inputStream);
    }

    public static WebElement getElementById(String id) {
        return getAndroidDriver().findElement(AppiumBy.id(id));
    }
}
