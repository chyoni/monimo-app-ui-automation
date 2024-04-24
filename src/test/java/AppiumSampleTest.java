import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Set;

@Slf4j
public class AppiumSampleTest {

    public static AndroidDriver driver;

    /*@BeforeAll
    public static void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("HVA1FG23");

        URL appiumServer = URI
                .create("http://0.0.0.0:4723")
                .toURL();
        driver = new AndroidDriver(appiumServer, options);
    }*/

    @Test
    public void test() {
        /*System.out.println("First driver.getContext() = " + driver.getContext());

        Set<String> contextHandles = driver.getContextHandles();
        for (String contextHandle : contextHandles) {
            System.out.println("contextHandle = " + contextHandle);
            if (contextHandle.contains("WEBVIEW")) {
                driver.context(contextHandle);
            }
        }

        System.out.println("Last driver.getContext() = " + driver.getContext());*/
        driver.quit();
    }

    @Test
    public void test2() {
        // tesseract를 brew로 설치하면, /usr/local/lib에 libtesseract.dylib가 있을건데 이 경로를 jna를 통해서 라이브러리를 가져올 때 검사하지를 않음.
        // 그래서 시스템 프로퍼티로 이 경로를 추가한다.
        System.setProperty("jna.library.path", "/usr/local/lib");

        /* OpenCV 영역
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image = Imgcodecs.imread("/Users/choichiwon/monimo/monimo-app-ui-automation/src/test/resources/assets/img.png");
        // 그레이스케일
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
        // 이진화
        Imgproc.threshold(image, image, 100, 255, Imgproc.THRESH_BINARY);
        */

        Tesseract tesseract = new Tesseract();

        // tessdata 경로를 넣어주면 된다.
        // tessdata 경로는 brew로 설치하면 'brew info tesseract' 로 확인 가능
        tesseract.setDatapath("/usr/local/Cellar/tesseract/5.3.4_1/share/tessdata");

        ///usr/local/Cellar/tesseract/5.3.4_1/share/tessdata 이 tessdata안에 언어팩을 넣어두는데 언어팩 중 한글 언어팩을 다운받아서 이 경로에 넣어주자.
        // 그리고 아래처럼 Kor 언어팩으로 OCR하겠다고 하면 됨.
        tesseract.setLanguage("kor");

        try {
            String result = tesseract.doOCR(new File("/Users/choichiwon/monimo/monimo-app-ui-automation/src/test/resources/assets/img.png"));

            System.out.println("result = " + result);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}
