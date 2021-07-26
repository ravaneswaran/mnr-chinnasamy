package com.mnrc.administration.changepassword.success

import com.mnrc.administration.MNRCAdministrationBaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import net.bytebuddy.utility.RandomString
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import java.util.Date

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/change-password/change-password-success.feature"),
  glue = Array("com.mnrc.administration.changepassword.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ChangePasswordSuccessIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  var firstName: String = RandomString.make()
  var emailId: String = String.format("%s@test.com", firstName)
  var password: String = RandomString.make()
  var newPassword: String = RandomString.make()
  var mobileNo: String = String.valueOf(new Date().getTime).substring(0, 11)

  Given("""the user is created by the admin""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("firstName")).sendKeys(this.firstName)
    this.webDriver.findElement(By.id("emailId")).sendKeys(this.emailId)
    this.webDriver.findElement(By.id("mobileNo")).sendKeys(this.mobileNo)
    this.webDriver.findElement(By.id("create")).click()
    this.webDriver.get("http://localhost:8080/logout");
  }

  And("""the user has logged into the system to change the password""") { () =>
    this.webDriver.findElement(By.id("emailId")).sendKeys(this.emailId);
    this.webDriver.findElement(By.id("password")).sendKeys("welcome");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.get("http://localhost:8080/change-password");
  }

  When("""the user submits the change password form and hits the submit button""") { () =>
    this.webDriver.findElement(By.id("oldPassword")).sendKeys("welcome")
    this.webDriver.findElement(By.id("newPassword")).sendKeys(this.newPassword)
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys(this.newPassword)
    this.webDriver.findElement(By.id("change-my-password")).click()
  }

  Then("""the user is expected to see the login page""") { () =>
    val title = this.webDriver.getTitle
    assert("MNRC-Administration : Login".equals(title))
  }

  And("""the user is able to login with the new password""") { () =>
    this.webDriver.findElement(By.id("emailId")).sendKeys(this.emailId);
    this.webDriver.findElement(By.id("password")).sendKeys(this.newPassword);
    this.webDriver.findElement(By.id("login")).click();
    val title = this.webDriver.getTitle
    assert("MNRC-Administration : User Creation".equals(title))
    this.webDriver.close()
  }
}
