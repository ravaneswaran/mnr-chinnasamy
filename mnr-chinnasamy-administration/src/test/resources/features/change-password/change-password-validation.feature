Feature: Change Password Validations

  Scenario: When the user submits the form with empty old password
    Given the user has logged into the system
    And the user hits the change password url on the address bar
    When the user tries to submit the form without filling the old password
    Then the user is expected to see the error message 'Old password should not be empty'

  Scenario: When the user submits the form with empty new password
    Given the user has logged into the system
    And the user hits the change password url on the address bar
    When the user has filled the old password and left other two fields empty and submits the form
    Then the user is expected to see the error message 'New Password should not be empty'

  Scenario: When the user submits the form with empty confirm password
    Given the user has logged into the system
    And the user hits the change password url on the address bar
    When the user has filled the old and new password and left confirm password field empty and submits the form
    Then the user is expected to see the error message 'Confirm Password should not be empty'

  Scenario: When the user submits the form with new and confirm password are different
    Given the user has logged into the system
    And the user hits the change password url on the address bar
    When the user has filled the old, new and confirm passwords with different values
    Then the user is expected to see the error message 'New and confirm password do not match'

  Scenario: When the user submits the form with old, new and confirm password are same as the existing one
    Given the user has logged into the system
    And the user hits the change password url on the address bar
    When the user has filled the old, new and confirm passwords with same value as the existing one
    Then the user is expected to see the error message 'Old password and New Password are same'