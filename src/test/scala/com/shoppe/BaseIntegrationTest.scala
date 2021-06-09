package com.shoppe

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.jupiter.api.{AfterAll, BeforeAll}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class BaseIntegrationTest extends ScalaDsl with EN{

  var webDriver: WebDriver = null

  @BeforeAll
  def setUP():Unit = {
    System.out.println("Before All")
    System.setProperty("webdriver.gecko.driver","classpath:geckodriver");
    this.webDriver = new FirefoxDriver()
  }

  def getWebDriver(): WebDriver = {
    this.webDriver;
  }

  @AfterAll
  def closeWebDriver(): Unit = {
    System.out.println("After All")
    this.webDriver.close()
    this.webDriver = null
  }

}

