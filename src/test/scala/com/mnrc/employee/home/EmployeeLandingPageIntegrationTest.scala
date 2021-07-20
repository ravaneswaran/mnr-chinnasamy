package com.mnrc.employee.home

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
  features = Array("classpath:features/employee/employee-landing-page.feature"),
  glue = Array("com.mnrc.employee.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class EmployeeLandingPageIntegrationTest extends BaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user opens up a browser and logs in""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
  }

  Then("""the user suppose to see the employee create page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Employee Create".equals(pageTitle))
    this.webDriver.close()
  }
}
