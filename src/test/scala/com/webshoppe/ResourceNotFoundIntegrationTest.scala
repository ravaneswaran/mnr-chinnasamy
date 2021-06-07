package com.webshoppe

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.runner.RunWith


@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/resource-not-found.feature"),
  glue = Array("com.webshoppe")/*,
  plugin = Array("pretty", "html:target/cucumber/html")*/)
class ResourceNotFoundIntegrationTest extends ScalaDsl with EN{

  Given("""A user opens up a browser`""") { () =>
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.scala.PendingException()
  }
  When("""He hits the following url {string}""") { (string: String) =>
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.scala.PendingException()
  }
  Then("""The browser should open a {int} page""") { (int1: Int) =>
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.scala.PendingException()
  }
  Then("""{string} should be the title of the page.""") { (string: String) =>
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.scala.PendingException()
  }
}