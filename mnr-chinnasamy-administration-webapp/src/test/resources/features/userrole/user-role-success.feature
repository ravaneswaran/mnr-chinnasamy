Feature: User Role successfully added

  Scenario: The user successfully created a new user role
    Given the user has logged into the system
    And  the user is on the user role page
    When the user tries to submit the form filling a valid user role name
    Then the user is expected to see the newly created user role on the page