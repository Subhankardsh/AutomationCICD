@tag
Feature: Error validations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Login with the email <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email                | password  |
      | rahuldash@gkmail.com | Rahul123 |
