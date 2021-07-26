Feature: Login Success

  Scenario: User logging into the system successfully
    Given the user is trying to login with empty email id "almighty@test.com" and password "almighty"
    Then the user should see the admin creation page