@carousell
Feature: Product Listing
  As a user,
  I want to list an item to sell and make it visible on the product listing page for the users.

  Scenario: List an item and verify the listing appears under recent Filter
    Given User is logged in
    When User lists an item under Everything Else category
    Then User verifies the item listed under recent in Everything Else category
