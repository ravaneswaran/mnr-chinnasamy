Feature: Payment gateway successfully added

  Scenario: The user successfully created a new payment gateway
    Given the user has logged into the system
    And  the user is on the payment gateway page
    When the user tries to submit the form filling valid payment gateway parameters
    Then the user is expected to see the newly created payment gateway on the page