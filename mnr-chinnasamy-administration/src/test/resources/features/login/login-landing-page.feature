Feature: Login Landing Page

  Scenario: User wants the login page
    Given the user has typed the following address in browser address bar "http://localhost:8080/"
    Then the user should see the login page
