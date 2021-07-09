Feature: Changing the password successfully

  Scenario: User is changing the password successfully
    Given the user is created by the admin
    And the user has logged into the system to change the password
    When the user submits the change password form and hits the submit button
    Then the user is expected to see the login page
    And the user is able to login with the new password