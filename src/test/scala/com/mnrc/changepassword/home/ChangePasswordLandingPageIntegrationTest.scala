package com.mnrc.changepassword.home

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
  features = Array("classpath:features/change-password/change-password-landing-page.feature"),
  glue = Array("com.mnrc.changepassword.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ChangePasswordLandingPageIntegrationTest extends BaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user has logged into the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
  }

  When("""the user hits the url change password url on the address bar""") { () =>
    this.webDriver.get("http://localhost:8080/change-password")
  }

  Then("""the user is expected to see the change password page""") { () =>
    val title = this.webDriver.getTitle
    assert("Shoppe : Change Password".equals(title))
    this.webDriver.close()
  }

}
