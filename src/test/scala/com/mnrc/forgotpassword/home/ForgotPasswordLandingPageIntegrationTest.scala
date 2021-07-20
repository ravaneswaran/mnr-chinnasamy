package com.mnrc.forgotpassword.home

import com.mnrc.BaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/forgot-password/forgot-password-landing-page.feature"),
  glue = Array("com.mnrc.forgotpassword.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ForgotPasswordLandingPageIntegrationTest extends BaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user has typed the following address in browser address bar {string}""") { (url: String) =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url);
  }

  Then("""the user should see the forgot password page""") { () =>
    val title = this.webDriver.getTitle
    assert("Shoppe : Forgot Password".equals(title))
    this.webDriver.close()
  }
}
