package com.shoppe.signup.error

import com.shoppe.BaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/signup/signup-error-conditions.feature"),
  glue = Array("com.shoppe.signup.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class SignUpErrorsConditionsIntegrationTest extends BaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""the user has not filled the first name in the signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
  }

  When("""the user tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("register")).click()
  }

  Then("""The user should redirected to the same sign up form page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Sign Up".equals(pageTitle))
  }

  Then("""the user should see the error message {string}""") { (errorMessage: String) =>
    val errorMsg = this.webDriver.findElement(By.id("error-message")).getText
    assert(errorMessage.equals(errorMsg))
  }
}
