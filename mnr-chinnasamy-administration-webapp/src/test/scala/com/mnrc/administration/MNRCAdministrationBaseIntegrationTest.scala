package com.mnrc.administration

import io.cucumber.scala.{EN, ScalaDsl}
import net.bytebuddy.utility.RandomString
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.{By, WebDriver}

import java.util.Date

class MNRCAdministrationBaseIntegrationTest extends ScalaDsl with EN{

  def almightyCreatingNewAdminAndLoggingOut(webDriver : WebDriver, firstName: String, emailId: String, mobileNo: String): Unit ={
    webDriver.get("http://localhost:8080/administration");
    webDriver.findElement(By.id("emailId")).sendKeys("almighty@test.com");
    webDriver.findElement(By.id("password")).sendKeys("almighty");
    webDriver.findElement(By.id("login")).click();
    webDriver.findElement(By.id("firstName")).sendKeys(firstName)
    webDriver.findElement(By.id("emailId")).sendKeys(emailId)
    webDriver.findElement(By.id("mobileNo")).sendKeys(mobileNo)
    webDriver.findElement(By.id("uniqueId")).sendKeys(String.valueOf(new Date().getTime).substring(0,11))
    webDriver.findElement(By.id("create")).click()
    webDriver.get("http://localhost:8080/administration/logout");
    webDriver.get("http://localhost:8080/administration");
  }

  def userHasloggedIn(webDriver : WebDriver, emailId: String, password: String): Unit = {
    webDriver.get("http://localhost:8080/administration");
    webDriver.findElement(By.id("emailId")).sendKeys(emailId);
    webDriver.findElement(By.id("password")).sendKeys(password);
    webDriver.findElement(By.id("login")).click();
  }

  def clickingUserRoleMenuItem(webDriver : WebDriver): Unit ={
    webDriver.findElement(By.id("app-launcher")).click();
    webDriver.findElement(By.id("userRole")).click();
  }

  def clickingUserCreationMenuItem(webDriver : WebDriver): Unit ={
    webDriver.findElement(By.id("app-launcher")).click();
    webDriver.findElement(By.id("userCreation")).click();
  }

  def clickingPaymentGatewayMenuItem(webDriver : WebDriver): Unit ={
    webDriver.findElement(By.id("app-launcher")).click();
    webDriver.findElement(By.id("paymentGateway")).click();
  }

  def generateRandomString(): String = {
    RandomString.make();
  }

  def selectFromDropDown(webDriver : WebDriver, id: String, value: String): Unit = {
    val userRole: Select = new Select(webDriver.findElement(By.id(id)));
    var index = 0;
    var indexToBeSelected = 0;
    userRole.getOptions.forEach(option => {
      if(!value.equals(option.getText)){
        index += 1
      } else {
        indexToBeSelected = index
      }})
    userRole.selectByIndex(indexToBeSelected)
  }
}

