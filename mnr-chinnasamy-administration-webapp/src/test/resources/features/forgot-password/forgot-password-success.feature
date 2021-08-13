Feature: Forgot Password Success

  Scenario: User has got the forgotten password successfully
    Given the user has entered his/her registered mail id with the system
    When the user clicks the submit button
    Then the user should see the password sent page