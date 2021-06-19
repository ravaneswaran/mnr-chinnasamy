Feature: To validate Admin form parameters on the browset

  Scenario: When the first name parameter is empty

    Given the user has not filled the first name in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And the user should see first name error 'First Name should not be empty'

  Scenario: When the email id parameter is empty

    Given the user has filled the first name but not the email id in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And the user should see email id error 'Email Id should not be empty'