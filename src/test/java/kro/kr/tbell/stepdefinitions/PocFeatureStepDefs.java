package kro.kr.tbell.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kro.kr.tbell.AppiumConnector;
import kro.kr.tbell.Constants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Slf4j
public class PocFeatureStepDefs {

    @When("개발 HTTPS 서버 버튼 클릭")
    public void clickDevHttpsServerBtn() {
        WebElement devHttpsServerBtn =
                AppiumConnector.getElementById(Constants.DEV_HTTPS_SERVER_BUTTON_ID);

        devHttpsServerBtn.click();
    }


    @Then("간편 비밀번호 입력 문구가 노출된다")
    public void assertSimplePasswordText() throws InterruptedException {

        try {
            AppiumConnector
                    .getElementById(Constants.SIMPLE_PASSWORD_TEXT_ID)
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            log.info("[assertSimplePasswordText]: 3초 대기 후 다시 시도");
            Thread.sleep(3000);

            AppiumConnector
                    .getElementById(Constants.SIMPLE_PASSWORD_TEXT_ID)
                    .isDisplayed();
        }
    }
}
