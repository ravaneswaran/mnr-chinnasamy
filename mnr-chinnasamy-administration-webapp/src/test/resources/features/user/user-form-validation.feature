Feature: To validate Admin form parameters on the browser

  Scenario: When the user role parameter is not selected
    Given the admin has not selected the user role parameter
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'Please select a valid user role...'

  Scenario: When the mobile no parameter is empty
    Given the admin has selected the user role and filled the first name, email id but not the mobile no in the user form
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'Mobile Number should not be empty'

  Scenario: When the mobile no parameter is not in enough length
    Given the admin has selected the user role and filled the first name, email id and mobile number with less than 10 characters in the user form
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'Mobile Number should be 10 characters in length'

  Scenario: When the first name parameter is empty
    Given the admin has selected the user role and not filled the first name, email id but not the mobile no in the user form
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'First name should not be empty'

  Scenario: When the email id parameter is empty
    Given the admin has selected the user role and filled the first name but not the email id in the user form
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'Email Id should not be empty'

  Scenario: When the email id parameter is not in format
    Given the admin has selected the user role and filled the first name but the email id in wrong format
    When the admin tries to submit the page
    Then The admin should redirected to the same user create page
    And the admin should see the error 'Email Id not in valid format'