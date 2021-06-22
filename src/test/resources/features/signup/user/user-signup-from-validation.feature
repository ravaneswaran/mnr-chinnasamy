Feature: To validate Sign Up parameters on the server side

  Scenario: When the first name parameter is empty

    Given the user has not filled the first name in the signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'First name should not be empty'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When the email id parameter is empty

    Given the user has filled the first name and not the email id in the signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Email Id should not be empty'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When empty mobile number is submitted

    Given the user filled the first name, email id and not the mobile number in the signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Mobile Number should not be empty'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When the mobile number parameter is short in length

    Given the user has filled the first name, email id and the mobile number is short in length in signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Mobile Number should be 10 characters in length'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When the password parameter is empty

    Given the user has filled the first name, email id, mobile number and left password field empty in signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Password should not be empty'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When the confirm password parameter is empty

    Given the user has filled all the mandatory fields except confirm password field empty in signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Confirm password should not be empty'
    And The user should see the error message in 'rgb(255, 0, 0)'

  Scenario: When the password and confirm password parameter are different

    Given the user has filled all the mandatory fields with different password and confirm password in signup form
    When the user tries to submit the page
    Then The user should redirected to the same sign up form page
    And The user should see the error message 'Password and Confirm password should be same'
    And The user should see the error message in 'rgb(255, 0, 0)'
