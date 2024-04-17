package kro.kr.tbell.stepDefs;

import io.cucumber.java.en.Given;
import kro.kr.tbell.AppiumConnector;
import kro.kr.tbell.Constants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@Slf4j
public class pocFeatureStepDefs {

    @Given("개발 HTTPS 서버 버튼 클릭")
    public void clickDevHttpsServerBtn() {
        WebElement devHttpsServerBtn =
                AppiumConnector.getElementById(Constants.DEV_HTTPS_SERVER_BUTTON_ID);

        devHttpsServerBtn.click();
    }
}
