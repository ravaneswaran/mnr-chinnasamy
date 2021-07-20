package com.mnrc.changepassword.error

import com.mnrc.BaseIntegrationTest
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
  features = Array("classpath:features/change-password/change-password-validation.feature"),
  glue = Array("com.mnrc.changepassword.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ChangePasswordValidationIntegrationTest extends BaseIntegrationTest {

  var webDriver: WebDriver = null

  Given("""the user has logged into the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
  }

  And("""the user hits the change password url on the address bar""") { () =>
    this.webDriver.get("http://localhost:8080/change-password");
  }

  When("""the user tries to submit the form without filling the old password""") { () =>
    this.webDriver.findElement(By.id("change-my-password")).click()
  }

  Then("""the user is expected to see the error message {string}""") { (errorMessage: String) =>
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMessage))
    this.webDriver.close();
  }

  When("""the user has filled the old password and left other two fields empty and submits the form""") { () =>
    this.webDriver.findElement(By.id("oldPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("change-my-password")).click()
  }

  When("""the user has filled the old and new password and left confirm password field empty and submits the form""") { () =>
    this.webDriver.findElement(By.id("oldPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("newPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("change-my-password")).click()
  }

  When("""the user has filled the old, new and confirm passwords with different values""") { () =>
    this.webDriver.findElement(By.id("oldPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("newPassword")).sendKeys("test");
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys("one");
    this.webDriver.findElement(By.id("change-my-password")).click()
  }

  When("""the user has filled the old, new and confirm passwords with same value as the existing one""") { () =>
    this.webDriver.findElement(By.id("oldPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("newPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys("welcome");
    this.webDriver.findElement(By.id("change-my-password")).click()
  }
}
