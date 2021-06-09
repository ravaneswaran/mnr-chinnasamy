package com.shoppe

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.HttpStatus

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/resource-not-found.feature"),
  glue = Array("com.shoppe"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ResourceNotFoundIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null

  Given("""A user opens up a browser`""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
  }

  When("""He hits the following url {string}""") { (url: String) =>
    this.webDriver.get(url)
  }

  Then("""The browser should open a {int} page""") { (httpStatus: Int) =>
    assert(httpStatus == HttpStatus.NOT_FOUND.value())
  }

  Then("""{string} should be the title of the page.""") { (title: String) =>
    val pageTitle: String = this.webDriver.getTitle
    assert(title.equals(pageTitle))
    this.webDriver.close()
  }

}