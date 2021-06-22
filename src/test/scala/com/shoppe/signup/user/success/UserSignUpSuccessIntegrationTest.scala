package com.shoppe.signup.user.success

import com.shoppe.BaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import scala.util.Random

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/signup/user/user-signup-success.feature"),
  glue = Array("com.shoppe.signup.user.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserSignUpSuccessIntegrationTest extends BaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""A user lands at the sign up page of the application""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup/user"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
  }

  Given("""the user started filling the sign up form""") { () =>
    val firstName = Random.nextString(10)
    val middleInitial = "M"
    val lastName = Random.nextString(10)
    val emailId = String.format("%s@gmail.com", firstName)
    val uniqueId = Random.nextInt(10)
    val password = Random.nextString(10)

    this.webDriver.findElement(By.id("firstName")).sendKeys(firstName)
    this.webDriver.findElement(By.id("middleInitial")).sendKeys(middleInitial)
    this.webDriver.findElement(By.id("lastName")).sendKeys(lastName)
    this.webDriver.findElement(By.id("emailId")).sendKeys(emailId)
    this.webDriver.findElement(By.id("uniqueId")).sendKeys(String.valueOf(uniqueId))
    this.webDriver.findElement(By.id("mobileNo")).sendKeys("1234567890")
    this.webDriver.findElement(By.id("password")).sendKeys(password)
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys(password)
  }

  When("""the user finished filling the form and hits the submit button""") { () =>
    this.webDriver.findElement(By.id("register")).click()
  }

  Then("""the user must see a page which gives direction to verify him\/her self""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : SignUp Success Notice".equals(pageTitle))
    this.webDriver.close()
  }

}
