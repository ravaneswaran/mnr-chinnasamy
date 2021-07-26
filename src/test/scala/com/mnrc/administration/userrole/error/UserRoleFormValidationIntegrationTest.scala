package com.mnrc.administration.userrole.error

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
  features = Array("classpath:features/userrole/user-role-form-validation.feature"),
  glue = Array("com.mnrc.administration.userrole.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserRoleFormValidationIntegrationTest extends MNRCAdministrationBaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""The user has logged in and lands on the user role page""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.userHasloggedIn(this.webDriver, "almighty@test.com", "almighty")
    this.clickingUserRoleMenuItem(this.webDriver)
  }

  When("""the user tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("create")).click();
  }

  Then("""the user should see the error {string}""") { (errorMessage: String) =>
    val errMsg = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMsg))
    this.webDriver.close()
  }

  When("""the user tries to submit the form with role name contains spaces""") { () =>
    val roleName = "Test Test"
    this.webDriver.findElement(By.id("roleName")).sendKeys(roleName)
    this.webDriver.findElement(By.id("create")).click();
  }
}
