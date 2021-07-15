package com.mnrc

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, WebDriver}

import java.security.SecureRandom

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
  }

  def generateRandomString(): String = {
    val length = 10
    val CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz"
    val CHAR_UPPER = CHAR_LOWER.toUpperCase
    val NUMBER = "0123456789"
    val DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER
    val sb = new StringBuilder(length)
    val random = new SecureRandom()

    for (i <- 0 until length) {
      val rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length)
      val rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt)
      sb.append(rndChar)
    }
    sb.toString
  }
}

