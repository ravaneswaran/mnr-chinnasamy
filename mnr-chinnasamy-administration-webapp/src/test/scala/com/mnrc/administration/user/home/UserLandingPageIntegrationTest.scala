package com.mnrc.administration.user.home

import com.mnrc.administration.MNRCAdministrationBaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/user/user-landing-page.feature"),
  glue = Array("com.mnrc.administration.user.home"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserLandingPageIntegrationTest extends MNRCAdministrationBaseIntegrationTest  {

  var webDriver: WebDriver = null

  Given("""the user opens up a browser and logs in""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.webDriver.get("http://localhost:8080/administration");
    this.webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    this.webDriver.findElement(By.id("password")).sendKeys("almighty");
    this.webDriver.findElement(By.id("login")).click();
  }

  Then("""the user suppose to see the user create page""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("MNRC-Administration : User Creation".equals(pageTitle))
    this.webDriver.close()
  }
}
