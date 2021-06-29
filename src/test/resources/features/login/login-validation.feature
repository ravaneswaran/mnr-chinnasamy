Feature: Login Validations

  Scenario: User login with empty email id
    Given the user is trying to login with empty email id
    Then the user should see the error 'Email id should not be empty'

  Scenario: User login with empty password
    Given the user is trying to login with empty password
    Then the user should see the error 'Password should not be empty'

  Scenario: User login with wrong email id and password
    Given the user is trying to login with wrong email id and password
    Then the user should see the error 'Email id and password combination does not exist'

