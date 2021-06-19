Feature: To validate Admin form parameters on the browset

  Scenario: When the first name parameter is empty

    Given the user has not filled the first name in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And the user should see the 'First Name should not be empty'