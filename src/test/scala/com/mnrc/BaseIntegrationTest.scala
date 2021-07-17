package com.mnrc

import io.cucumber.scala.{EN, ScalaDsl}
import net.bytebuddy.utility.RandomString
import org.openqa.selenium.{By, WebDriver}

class BaseIntegrationTest extends ScalaDsl with EN{

  def almightyCreatingNewAdminAndLoggingOut(webDriver : WebDriver, firstName: String, emailId: String, mobileNo: String): Unit ={
    webDriver.get("http://localhost:8080");
    webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    webDriver.findElement(By.id("password")).sendKeys("almighty");
    webDriver.findElement(By.id("login")).click();
    webDriver.findElement(By.id("firstName")).sendKeys(firstName)
    webDriver.findElement(By.id("emailId")).sendKeys(emailId)
    webDriver.findElement(By.id("mobileNo")).sendKeys(mobileNo)
    webDriver.findElement(By.id("create")).click()
    webDriver.get("http://localhost:8080/logout");
    webDriver.get("http://localhost:8080");
  }

  def generateRandomString(): String = {
    RandomString.make();
  }

}

