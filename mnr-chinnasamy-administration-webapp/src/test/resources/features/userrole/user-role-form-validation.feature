Feature: To validate the user role form

  Scenario: When the user has submitted the empty form
    Given The user has logged in and lands on the user role page
    When the user tries to submit the page
    Then the user should see the error 'User Role name should not be empty'

  Scenario: When the user has submitted form with invalid name
    Given The user has logged in and lands on the user role page
    When the user tries to submit the form with role name contains spaces
    Then the user should see the error 'User Role name should not have any white space...'