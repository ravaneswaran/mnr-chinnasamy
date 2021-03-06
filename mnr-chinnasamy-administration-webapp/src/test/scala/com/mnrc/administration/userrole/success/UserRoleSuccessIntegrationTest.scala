package com.mnrc.administration.userrole.success

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
  features = Array("classpath:features/userrole/user-role-success.feature"),
  glue = Array("com.mnrc.administration.userrole.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class UserRoleSuccessIntegrationTest extends MNRCAdministrationBaseIntegrationTest{

  var webDriver: WebDriver = null
  val roleName = String.format("%s-%s", RandomString.make(), RandomString.make())

  Given("""the user has logged into the system""") { () =>
    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver")
    this.webDriver = new FirefoxDriver()
    this.userHasloggedIn(this.webDriver, "almighty@test.com", "almighty")
  }

  And("""the user is on the user role page""") { () =>
    this.clickingUserRoleMenuItem(this.webDriver)
  }

  When("""the user tries to submit the form filling a valid user role name""") { () =>
    this.webDriver.findElement(By.id("roleName")).sendKeys(this.roleName)
    this.webDriver.findElement(By.id("create")).click();
  }

  Then("""the user is expected to see the newly created user role on the page""") { () =>
    val textTags = this.webDriver.findElements(By.tagName("span"))
    var found = false;
    textTags.forEach(textTab => {
      val text = textTab.getText
      if(text.equals(String.format("%s (0 users)", this.roleName.toUpperCase))){
        found = true;
      }
    })
    assert(found)
    this.webDriver.close()
  }
}
