
@tag
Feature: Purchase the order from Ecommerce Website
	I want to use this template for my feature file

	Background:
	Given : I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I added product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page.

    Examples: 
      | name  							    | password         | productName   |
      | rahulshetty@gmail.com   | Iamking@000      | ZARA COAT 3   |
