package com.mnrc.signup.user.error

import com.mnrc.BaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
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

  Given("""the user has filled the first name and not the email id in the signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
  }

  Then("""The user should see the error message {string}""") { (errorMessage: String) =>
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMessage))
  }

  Given("""the user filled the first name, email id and not the mobile number in the signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
  }

  Given("""the user has filled the first name, email id and the mobile number is short in length in signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("1234567")
  }

  Given("""the user has filled the first name, email id, mobile number and left password field empty in signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("1234567890")
  }

  Given("""the user has filled all the mandatory fields except confirm password field empty in signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("1234567890")
    this.webDriver.findElement(By.id("password")).sendKeys("testpassword")
  }

  Given("""the user has filled all the mandatory fields with different password and confirm password in signup form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("1234567890")
    this.webDriver.findElement(By.id("password")).sendKeys("testpassword")
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys("passwordtest")
  }

}
