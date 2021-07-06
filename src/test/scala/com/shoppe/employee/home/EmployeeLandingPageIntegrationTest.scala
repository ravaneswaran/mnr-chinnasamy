package com.shoppe.employee.home

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/employee/employee-landing-page.feature"),
  glue = Array("com.shoppe.admin.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class EmployeeLandingPageIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""the user opens up a browser""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
  }

  When("""the user hits the following url {string}""") { (url: String) =>
    this.webDriver.get(url)
  }

  Then("""the user suppose to see the admin page of the shoppe app""") { () =>
    val titleOfThePage = this.webDriver.getTitle
    assert("Shoppe : Employee Creation".equals(titleOfThePage))
    this.webDriver.close()
  }

}
