@carousell
Feature: Product Listing
  As a user,
  I want to list an item to sell and make it visible on the product listing page for the users.

  Scenario: List an item and verify the listing appears under recent Filter
    Given User logs in
    When User lists an item
    Then User verifies the item listed under recent
