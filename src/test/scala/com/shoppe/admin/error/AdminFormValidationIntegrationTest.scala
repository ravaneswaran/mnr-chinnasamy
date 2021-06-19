package com.shoppe.admin.error

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

  Then("""The user should redirected to the same sign up form page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Admin Creation".equals(pageTitle))
  }

  Then("""the user should see first name error {string}""") { (errorMessage: String) =>
    val errorMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert("First name should not be empty".equals(errorMessage))
    this.webDriver.close()
  }

  Given("""the user has filled the first name but not the email id in the admin form""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/admin")
    this.webDriver.findElement(By.id("firstName")).sendKeys("Ravaneswaran")
  }

  Then("""the user should see email id error {string}""") { (errorMessage: String) =>
    val errorMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert("Email Id should not be empty".equals(errorMessage))
    this.webDriver.close()
  }
}
