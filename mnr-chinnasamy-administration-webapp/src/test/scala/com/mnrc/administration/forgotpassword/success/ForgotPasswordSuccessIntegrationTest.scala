package com.mnrc.administration.forgotpassword.success

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
  features = Array("classpath:features/forgot-password/forgot-password-success.feature"),
  glue = Array("com.mnrc.administration.forgotpassword.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ForgotPasswordSuccessIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user has entered his\/her registered mail id with the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration/forgot-password");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com")
  }

  When("""the user clicks the submit button""") { () =>
    this.webDriver.findElement(By.id("mail-my-password")).click()
  }

  Then("""the user should see the password sent page""") { () =>
    val title =  this.webDriver.getTitle
    assert("MNRC-Administration : Password Sent".equals(title))
    this.webDriver.close()
  }

}
