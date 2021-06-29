Feature: Forgot Password Validations

  Scenario: User submitting the form with empty email id
    Given When the user submits the form with the empty email id
    Then the user should see the error 'Email id should not be empty'

