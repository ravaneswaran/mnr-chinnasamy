Feature: Signup Landing Page feature
  Scenario: When the use hits the sign up page of shoppe

    Given the user opens up a browser
    When the user hits the following url 'http://localhost:8080/signup'
    Then the user suppose to see the sign in page of the shoppe app
