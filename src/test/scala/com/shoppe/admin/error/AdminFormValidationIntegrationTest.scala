package com.shoppe.admin.error

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/admin/admin-from-validation.feature"),
  glue = Array("com.shoppe.admin.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class AdminFormValidationIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""the user has not filled the first name in the admin form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
  }

  When("""the user tries to submit the page""") { () =>
    this.webDriver.findElement(By.id("create")).click();
  }

  Then("""The user should redirected to the same admin form page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Admin Creation".equals(pageTitle))
  }

  Given("""the user has filled the first name but not the email id in the admin form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
  }

  Given("""the user has filled the first name but the email id in wrong format""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test")
  }

  Given("""the user has filled the first name, email id but not the mobile no in the admin form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
  }

  Given("""the user has filled the first name, email id and mobile number with less than 10 characters in the admin form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
    this.webDriver.findElement(By.id("emailId")).sendKeys("test@test.com")
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("345679999")
  }

  And("""the user should see the error {string}""") { (errMessage: String) =>
    val errorMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errMessage.equals(errorMessage))
    this.webDriver.close()
  }

}
