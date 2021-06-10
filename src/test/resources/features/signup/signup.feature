Feature: Sign up Feature
  Scenario: the user signs up successfully

    Given A user lands at the sign up page of the application
    And the user started filling the sign up form
    When the user finished form filling and hits the submit button
    Then the user must see a page which gives direction to verify him/her self
