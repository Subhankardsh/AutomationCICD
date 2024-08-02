@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Login with the email <email> and password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submiit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | email                | password  | productName |
      | rahuldash@gkmail.com | Rahul@123 | ZARA COAT 3 |
