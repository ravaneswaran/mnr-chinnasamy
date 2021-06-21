Feature: To validate Sign Up parameters on the server side

  Scenario: When the first name parameter is empty

    Given the user has not filled the first name in the signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message in 'rgb(255, 0, 0)'
