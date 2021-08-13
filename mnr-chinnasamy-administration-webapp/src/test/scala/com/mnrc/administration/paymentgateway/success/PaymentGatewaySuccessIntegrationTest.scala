package com.mnrc.administration.paymentgateway.success

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
  features = Array("classpath:features/payment-gateway/payment-gateway-success.feature"),
  glue = Array("com.mnrc.administration.paymentgateway.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class PaymentGatewaySuccessIntegrationTest extends MNRCAdministrationBaseIntegrationTest{

  var webDriver: WebDriver = null
  var paymentGatewayName: String = RandomString.make()

  Given("""the user has logged into the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.userHasloggedIn(this.webDriver, "almighty@test.com", "almighty")
  }

  And("""the user is on the payment gateway page""") { () =>
    this.clickingPaymentGatewayMenuItem(this.webDriver)
  }

  When("""the user tries to submit the form filling valid payment gateway parameters""") { () =>
    this.webDriver.findElement(By.id("name")).sendKeys(paymentGatewayName)
    this.webDriver.findElement(By.id("merchantId")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("paymentGatewayKey")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("paymentGatewaySecret")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("callbackUrl")).sendKeys(RandomString.make())
    this.webDriver.findElement(By.id("create")).click()
  }

  Then("""the user is expected to see the newly created payment gateway on the page""") { () =>
    val textTags = this.webDriver.findElements(By.tagName("span"))
    var found = false;
    textTags.forEach(textTab => {
      val text = textTab.getText
      if(text.equals(String.format("%s", this.paymentGatewayName))){
        found = true;
      }
    })
    assert(found)
    this.webDriver.close()
  }

}
