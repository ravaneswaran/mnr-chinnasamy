package com.mnrc.administration.paymentgateway.error

import com.mnrc.administration.MNRCAdministrationBaseIntegrationTest
import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.spring.CucumberContextConfiguration
import net.bytebuddy.utility.RandomString
import org.junit.runner.RunWith
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{By, WebDriver}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/payment-gateway/payment-gateway-form-validation.feature"),
  glue = Array("com.mnrc.administration.paymentgateway.error"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class PaymentGatewayValidationIntegrationTest extends MNRCAdministrationBaseIntegrationTest{

  var webDriver: WebDriver = null

  Given("""The user has logged in and lands on the payment gateway page""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.userHasloggedIn(this.webDriver, "almighty@test.com", "almighty")
    this.clickingPaymentGatewayMenuItem(this.webDriver)
  }

  Then("""the user should see the error {string}""") { (errorMessage: String) =>
    val errMsg = this.webDriver.findElement(By.id("errorMessage")).getText
    assert(errorMessage.equals(errMsg))
    this.webDriver.close()
  }

  When("""the user tries to submit the page with empty payment gateway name""") { () =>
    this.webDriver.findElement(By.id("create")).click();
  }

  When("""the user tries to submit the page with merchant id empty""") { () =>
    this.webDriver.findElement(By.id("name")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("create")).click();
  }

  When("""the user tries to submit the page with payment gateway key empty""") { () =>
    this.webDriver.findElement(By.id("name")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("merchantId")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("create")).click();
  }

  When("""the user tries to submit the page with payment gateway secret empty""") { () =>
    this.webDriver.findElement(By.id("name")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("merchantId")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("paymentGatewayKey")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("create")).click();
  }

  When("""the user tries to submit the page with call back url empty""") { () =>
    this.webDriver.findElement(By.id("name")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("merchantId")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("paymentGatewayKey")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("paymentGatewaySecret")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("create")).click();
  }
}
