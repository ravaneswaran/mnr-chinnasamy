Feature: To validate Admin form parameters on the browset

  Scenario: When the mobile no parameter is empty
    Given the user has filled the first name, email id but not the mobile no in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same admin form page
    And the user should see the error 'Mobile Number should not be empty'

  Scenario: When the mobile no parameter is not in enough length
    Given the user has filled the first name, email id and mobile number with less than 10 characters in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same admin form page
    And the user should see the error 'Mobile Number should be 10 characters in length'

  Scenario: When the first name parameter is empty
    Given the user has not filled the first name in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same admin form page
    And the user should see the error 'First name should not be empty'

  Scenario: When the email id parameter is empty
    Given the user has filled the first name but not the email id in the admin form
    When the user tries to submit the page
    Then The user should redirected to the same admin form page
    And the user should see the error 'Email Id should not be empty'

  Scenario: When the email id parameter is not in format
    Given the user has filled the first name but the email id in wrong format
    When the user tries to submit the page
    Then The user should redirected to the same admin form page
    And the user should see the error 'Email Id not in valid format'