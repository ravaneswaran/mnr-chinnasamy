Feature: Forgot Password Landing Page

  Scenario: User wants to get his forgotten password
    Given the user has typed the following address in browser address bar "http://localhost:8080/forgot-password"
    Then the user should see the forgot password page