package com.shoppe.signup.success

import com.shoppe.BaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import scala.util.Random

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/signup/signup.feature"),
  glue = Array("com.shoppe.signup.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class SigningUpIntegrationTest extends BaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""A user lands at the sign up page of the application""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    val url = "http://localhost:8080/signup"
    this.webDriver = new FirefoxDriver()
    this.webDriver.get(url)
  }

  Given("""the user started filling the sign up form""") { () =>
    val firstName = Random.nextString(10)
    val middleInitial = "M"
    val lastName = Random.nextString(10)
    val emailId = String.format("%s@gmail.com", firstName)
    val uniqueId = Random.nextInt(10)
    val mobileNo = Random.nextInt(10)
    val password = Random.nextString(10)

    this.webDriver.findElement(By.id("firstName")).sendKeys(firstName)
    this.webDriver.findElement(By.id("middleInitial")).sendKeys(middleInitial)
    this.webDriver.findElement(By.id("lastName")).sendKeys(lastName)
    this.webDriver.findElement(By.id("emailId")).sendKeys(emailId)
    this.webDriver.findElement(By.id("uniqueId")).sendKeys(String.valueOf(uniqueId))
    this.webDriver.findElement(By.id("mobileNo")).sendKeys(String.valueOf(mobileNo))
    this.webDriver.findElement(By.id("password")).sendKeys(password)
    this.webDriver.findElement(By.id("confirmPassword")).sendKeys(password)
  }

  When("""the user finished form filling and hits the submit button""") { () =>
    this.webDriver.findElement(By.id("register")).click()
  }

  Then("""the user must see a page which gives direction to verify him\/her self""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Registration Successful".equals(pageTitle))
    //this.webDriver.close()
  }

}
