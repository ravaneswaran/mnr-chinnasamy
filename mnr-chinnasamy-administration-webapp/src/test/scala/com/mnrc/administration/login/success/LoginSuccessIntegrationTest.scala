package com.mnrc.administration.login.success

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
  features = Array("classpath:features/login/login-success.feature"),
  glue = Array("com.mnrc.administration.login.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class LoginSuccessIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user is trying to login with empty email id {string} and password {string}""") { (emailId: String, password: String) =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration");
    this.webDriver.findElement(By.id("emailId")).sendKeys(emailId);
    this.webDriver.findElement(By.id("password")).sendKeys(password);
    this.webDriver.findElement(By.id("login")).click();
  }

  Then("""the user should see the user role page""") { () =>
    val title = this.webDriver.getTitle
    assert("MNRC-Administration : User Role".equals(title))
    this.webDriver.close()
  }

}
