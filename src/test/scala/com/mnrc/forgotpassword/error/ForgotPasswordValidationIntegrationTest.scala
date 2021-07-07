package com.mnrc.forgotpassword.error

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/forgot-password/forgot-password-validation.feature"),
  glue = Array("com.shoppe.forgotpassword.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ForgotPasswordValidationIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""When the user submits the form with the empty email id""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/forgot-password");
    this.webDriver.findElement(By.id("mail-my-password")).click()
  }

  Then("""the user should see the error {string}""") { (errorMessage: String) =>
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMessage))
    this.webDriver.close()
  }

}
