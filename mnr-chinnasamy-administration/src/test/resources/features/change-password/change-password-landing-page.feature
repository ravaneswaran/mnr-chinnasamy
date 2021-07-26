Feature: Change Password Landing Page

  Scenario: User wants to change his/her password
    Given the user has logged into the system
    When the user hits the url change password url on the address bar
    Then the user is expected to see the change password page
