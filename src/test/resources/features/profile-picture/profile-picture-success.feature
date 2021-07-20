Feature: Profile Picture Change Success

  Scenario: The user successfully changed his/her profile picture
    Given the user has logged into the system
    And the user hits the My Info in the menu
    When the user tries to submit the form filling his/her profile picture
    Then the user is expected to see the image box with the uploaded image