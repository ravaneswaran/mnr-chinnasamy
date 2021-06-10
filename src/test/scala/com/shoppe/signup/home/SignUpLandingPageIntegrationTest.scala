package com.shoppe.signup.home

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
  features = Array("classpath:features/signup/signup-landing-page.feature"),
  glue = Array("com.shoppe.signup.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class SignUpLandingPageIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""the user opens up a browser""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
  }

  When("""the user hits the following url {string}""") { (url: String) =>
    this.webDriver.get(url)
  }

  Then("""the user suppose to see the sign in page of the shoppe app""") { () =>
    val titleOfThePage = this.webDriver.getTitle
    assert("Shoppe : Sign Up".equals(titleOfThePage))
    this.webDriver.close()
  }

}
