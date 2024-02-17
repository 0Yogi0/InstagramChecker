Feature: Login feature

  @tag1
  Scenario: Login with only username
    Given I am on login page
    When I enter username and I click login button
    Then I validate the user is not logged in

  @tag2
  Scenario: Login with only password
    Given I am on login page
    When I enter password and I click login button
    Then I validate the user is not logged in

  @tag3
  Scenario: Login with correct username and password
    Given I am on login page
    When I enter "<username>" and I enter "<password>" and I click on login button
    Then I validate the user is able to login

    Examples:
    | username | password | status |
    | user1234 |          | fail   |
    |          | password | fail   |
    | user1200 | password | success|