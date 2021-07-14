Feature: Profile Picture Validations

  Scenario: The user expects error messages when the user submit the form with the empty profile picture
    Given the user has logged into the system
    And the user hits the My Info in the menu
    When the user tries to submit the form without filling profile picture
    Then the user is expected to see the error message 'Profile picture found to be empty...'

  Scenario: The user expects error messages when the user submit the form with inappropriate file
    Given the user has logged into the system
    And the user hits the My Info in the menu
    When the user tries to submit the form with inappropriate file
    Then the error message should be 'Sorry!!! the file(%s) is not a valid one to be your profile pic'