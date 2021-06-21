@Admin
Feature: The application should be running
    Background: User is logged in
  #Scenario: user is on homepage
        Given user is on  home page


    @smoke @test1
    Scenario: Ability to login successfully and verify it
   # Given user is on  home page
        When user logs in with valid credentials
        Then user should see dashboard page

    @test2
    Scenario: Ability to complete a search and verify it
   # Given user is on  home page
        When user logs in with valid credentials
        Then user should see dashboard page
        When user search for "arrays"
        Then user should see results


    @test3
    Scenario: Ability to enroll in a course and verify it
   #Given user is on  home page
        And user logs in with valid credentials
        And user should see dashboard page
        When user clicks training set up icon to navigate Training SetUp
        And user picks Java for training
        Then user clicks to blue save button to navigate back to dashboard
        Then verify user can see that language at dropdown menu

  # Negative Test
    @test4
    Scenario Outline: User logins with invalid credentials <username> <password>
   # Given user is on  home page
        When the user logs in using "<email>" and "<password>"
        Then the user should not be able to login
        Examples:
            | email               | password |
            | test123@sdet.com    | 123456   |
      #|benchprep@sdet.com   | np6AxVIh |