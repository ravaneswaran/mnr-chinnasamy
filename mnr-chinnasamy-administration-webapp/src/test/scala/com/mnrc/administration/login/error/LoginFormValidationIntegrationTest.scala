package com.mnrc.administration.login.error

import com.mnrc.administration.MNRCAdministrationBaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/login/login-validation.feature"),
  glue = Array("com.mnrc.administration.login.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class LoginFormValidationIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user is trying to login with empty email id""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration");
    this.webDriver.findElement(By.id("login")).click();
  }

  Given("""the user is trying to login with empty password""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration");
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com");
    this.webDriver.findElement(By.id("login")).click();
  }

  Given("""the user is trying to login with wrong email id and password""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration");
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("test");
    this.webDriver.findElement(By.id("login")).click();
  }

  Then("""the user should see the error {string}""") { (errorMessage: String) =>
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMessage))
    this.webDriver.close()
  }

}
