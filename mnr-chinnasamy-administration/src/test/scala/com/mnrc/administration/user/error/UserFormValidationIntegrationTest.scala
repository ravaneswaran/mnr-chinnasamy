package com.mnrc.administration.user.error

import com.mnrc.administration.MNRCAdministrationBaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/user/user-form-validation.feature"),
  glue = Array("com.mnrc.administration.user.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserFormValidationIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the admin has not selected the user role parameter""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();

  }

  Given("""the admin has selected the user role and not filled the first name, email id but not the mobile no in the user form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();
    val userRoleSelect = new Select(this.webDriver.findElement(By.id("userRoleId")))
    userRoleSelect.selectByIndex(1)
  }

  Given("""the admin has selected the user role and filled the first name but not the email id in the user form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();
    val userRoleSelect = new Select(this.webDriver.findElement(By.id("userRoleId")))
    userRoleSelect.selectByIndex(1)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
  }

  Given("""the admin has selected the user role and filled the first name but the email id in wrong format""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();
    val userRoleSelect = new Select(this.webDriver.findElement(By.id("userRoleId")))
    userRoleSelect.selectByIndex(1)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test")
  }

  Given("""the admin has selected the user role and filled the first name, email id but not the mobile no in the user form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();
    val userRoleSelect = new Select(this.webDriver.findElement(By.id("userRoleId")))
    userRoleSelect.selectByIndex(1)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
  }

  Given("""the admin has selected the user role and filled the first name, email id and mobile number with less than 10 characters in the user form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("userCreation")).click();
    val userRoleSelect = new Select(this.webDriver.findElement(By.id("userRoleId")))
    userRoleSelect.selectByIndex(1)
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("345679999")
  }

 /* When("""the admin tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("create")).click();
  }*/

  When("""the admin tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("create")).click();
  }

  /*Then("""The admin should redirected to the same user page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("MNRC-Administration : User Creation".equals(pageTitle))
  }*/

  Then("""The admin should redirected to the same user create page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("MNRC-Administration : User Creation".equals(pageTitle))
  }

  /*And("""the admin should see the error {string}""") { (errMessage: String) =>
    val errorMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errMessage.equals(errorMessage))
    this.webDriver.close()
  }*/

  And("""the admin should see the error {string}""") { (errMessage: String) =>
    val errorMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errMessage.equals(errorMessage))
    this.webDriver.close()
  }

}
