package kro.kr.tbell;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
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
            synchronized (AppiumConnector.class) {
                if (androidDriver == null) {
                    Map<String, Object> env = initializeYaml();

                    String udid = (String) env.get("udid");
                    String url = (String) env.get("url");

                    try {
                        createAndroidDriver(url, udid);
                    } catch (MalformedURLException e) {
                        log.error("[getAndroidDriver]: MalformedURLException", e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return androidDriver;
    }

    private static void createAndroidDriver(String url, String udid) throws MalformedURLException {
        URL appiumServer = URI.create(url).toURL();
        UiAutomator2Options options = new UiAutomator2Options().setUdid(udid);

        androidDriver = new AndroidDriver(appiumServer, options);
    }

    private static Map<String, Object> initializeYaml() {
        try (InputStream inputStream = AppiumConnector.class
                .getClassLoader()
                .getResourceAsStream("env.yaml")) {

            if (inputStream == null) {
                throw new IllegalStateException("env.yaml not found.");
            }

            return yaml.load(inputStream);
        } catch (IOException e) {
            log.error("[initializeYaml]: Error occurred when loading yaml file ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 주어진 ID를 가진 웹 요소를 찾아 반환합니다.
     * 이 메서드는 안드로이드 드라이버를 사용하여 애플리케이션에서 해당 ID를 가진 요소를 검색합니다.
     *
     * @param id 찾고자 하는 웹 요소의 resource id 입니다.
     * @return WebElement 객체를 반환합니다. ID에 해당하는 요소가 없는 경우 {@code null}을 반환할 수 있습니다.
     * @throws org.openqa.selenium.NoSuchElementException 요소를 찾을 수 없는 경우 발생합니다.
     * @throws IllegalStateException AndroidDriver가 초기화되지 않았거나 접근할 수 없는 경우 발생합니다.
     * @throws org.openqa.selenium.WebDriverException 드라이버와의 통신 중 문제가 발생한 경우 발생합니다.
     * */
    public static WebElement getElementById(String id) {
        return getAndroidDriver().findElement(AppiumBy.id(id));
    }
}
