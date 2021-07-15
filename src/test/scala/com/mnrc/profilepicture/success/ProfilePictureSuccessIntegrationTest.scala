package com.mnrc.profilepicture.success

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.{File, IOException}
import java.util.Date
import javax.imageio.ImageIO

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/profile-picture/profile-picture-success.feature"),
  glue = Array("com.mnrc.profilepicture.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ProfilePictureSuccessIntegrationTest extends ScalaDsl with EN {

  var webDriver: WebDriver = null
  var file: File = null

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

  When("""the user tries to submit the form filling his\/her profile picture""") { () =>
    file = this.createTemporaryImageFile
    val uploadElement = this.webDriver.findElement(By.id("employee-info-profile-picture-browse"));
    uploadElement.sendKeys(file.getAbsolutePath)
    this.webDriver.findElement(By.id("employee-info-profile-picture-submit")).click();
  }

  Then("""the user is expected to see the image box with the uploaded image""") { () =>
    val pageTitle = this.webDriver.getTitle
    assert("Shoppe : Employee Information".equals(pageTitle))
  }

  @throws[IOException]
  def createTemporaryImageFile: File = {
    val width = 250
    val height = 250
    // Constructs a BufferedImage of one of the predefined image types.
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    // Create a graphics which can be used to draw into the buffered image
    val g2d = bufferedImage.createGraphics
    // fill all the image with white
    g2d.setColor(Color.white)
    g2d.fillRect(0, 0, width, height)
    // create a circle with black
    g2d.setColor(Color.black)
    g2d.fillOval(0, 0, width, height)
    // create a string with yellow
    g2d.setColor(Color.yellow)
    g2d.drawString("MNRC", 50, 120)
    // Disposes of this graphics context and releases any system resources that it is using.
    g2d.dispose()
    // Save as PNG
    val file = File.createTempFile(String.format("temp-image-%s", new Date().getTime), ".png")
    ImageIO.write(bufferedImage, "png", file)
    file
  }
}
