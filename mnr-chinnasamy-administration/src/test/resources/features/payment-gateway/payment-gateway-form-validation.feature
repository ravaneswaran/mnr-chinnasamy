Feature: To validate payment gateway form

  Scenario: When the user has submitted the form with empty name
    Given The user has logged in and lands on the payment gateway page
    When the user tries to submit the page with empty payment gateway name
    Then the user should see the error 'Name should not be empty'

  Scenario: When the user has submitted the form with empty merchant id
    Given The user has logged in and lands on the payment gateway page
    When the user tries to submit the page with merchant id empty
    Then the user should see the error 'Merchant Id cannot be empty'

  Scenario: When the user has submitted the form with empty payment gateway key
    Given The user has logged in and lands on the payment gateway page
    When the user tries to submit the page with payment gateway key empty
    Then the user should see the error 'Payment gateway key cannot be empty'

  Scenario: When the user has submitted the form with empty payment gateway secret
    Given The user has logged in and lands on the payment gateway page
    When the user tries to submit the page with payment gateway secret empty
    Then the user should see the error 'Payment gateway secret cannot be empty'

  Scenario: When the user has submitted the form with empty call back url
    Given The user has logged in and lands on the payment gateway page
    When the user tries to submit the page with call back url empty
    Then the user should see the error 'Callback URL cannot be empty'