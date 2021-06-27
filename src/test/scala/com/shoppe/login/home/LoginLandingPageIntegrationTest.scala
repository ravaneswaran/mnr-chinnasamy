package com.shoppe.login.home

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
  features = Array("classpath:features/login/login-landing-page.feature"),
  glue = Array("com.shoppe.login.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class LoginLandingPageIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""the user has typed the following address in browser address bar {string}""") { (address: String) =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(address);
  }

  Then("""the user should see the login page""") { () =>
    val title = this.webDriver.getTitle
    assert("Shoppe : Login".equals(title))
    this.webDriver.close()
  }
}
