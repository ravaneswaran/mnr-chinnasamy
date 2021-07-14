package com.mnrc.profilepicture.success

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features/profile-picture/profile-picture-success.feature"),
  glue = Array("com.mnrc.profilepicture.success"))
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
class ProfilePictureSuccessIntegrationTest extends ScalaDsl with EN {

  Given("""the user has logged into the system""") { () =>

  }

  And("""the user hits the My Info in the menu""") { () =>

  }

  When("""the user tries to submit the form filling his\/her profile picture""") { () =>

  }

  Then("""the user is expected to see the image box with the uploaded image""") { () =>

  }

}
