package com.mnrc.profilepicture.error

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import java.io.{File, FileOutputStream}
import java.util.{Collections, Date}

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/profile-picture/profile-picture-validation.feature"),
  glue = Array("com.mnrc.profilepicture.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ProfilePictureValidationIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null
  var file: File = null;

  Given("""the user has logged into the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()

    this.webDriver.get("http://localhost:8080");
    this.webDriver.findElement(By.id("emailId")).sendKeys("ravaneswaran@gmail.com");
    this.webDriver.findElement(By.id("password")).sendKeys("welcome");
    this.webDriver.findElement(By.id("login")).click();
  }

  And("""the user hits the My Info in the menu""") { () =>
    this.webDriver.findElement(By.id("app-launcher")).click();
    this.webDriver.findElement(By.id("my-info")).click();
  }

  When("""the user tries to submit the form without filling profile picture""") { () =>
    this.webDriver.findElement(By.id("employee-info-profile-picture-submit")).click();
  }

  When("""the user tries to submit the form with inappropriate file""") { () =>
    this.file = File.createTempFile(String.format("temp-%s", new Date().getTime), ".txt")
    val outputStream = new FileOutputStream(this.file)
    outputStream.write("some text content".getBytes())
    outputStream.close()

    val uploadElement = this.webDriver.findElement(By.id("employee-info-profile-picture-browse"));
    uploadElement.sendKeys(this.file.getAbsolutePath)
    this.webDriver.findElement(By.id("employee-info-profile-picture-submit")).click();
  }

  Then("""the user is expected to see the error message {string}""") { (errorMessage: String) =>
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMessage))
    this.webDriver.close();
  }

  Then("""the error message should be {string}""")  { (errorMessage: String) =>
    var errMsg = String.format(errorMessage, this.file.getName)
    val errMessage = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errMsg.equals(errMessage))
    this.webDriver.close();
  }
}
