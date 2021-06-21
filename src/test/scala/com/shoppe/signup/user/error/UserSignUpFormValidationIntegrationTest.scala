package com.shoppe.signup.user.error

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
  features = Array("classpath:features/signup/user/user-signup-from-validation.feature"),
  glue = Array("com.shoppe.signup.user.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserSignUpFormValidationIntegrationTest extends BaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""the user has not filled the first name in the signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
  }

  When("""the user tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("register")).click()
  }

  Then("""The user should redirected to the same sign up form page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : User Sign Up".equals(pageTitle))
  }

  And("""The user should see the error message in {string}""") { (color: String) =>
    val textColor = this.webDriver.findElement(By.id("errorMessage")).getCssValue("color")
    assert(color.equals(textColor))
    this.webDriver.close();
  }
}
