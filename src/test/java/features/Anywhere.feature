Feature:Finding apartment anywhere
  Using this search option user should be able to search apartments anywhere in the world
  Background:User should be on airbnb website
    Given User navigate to airbnb website
    @AnywhereRegionStays
    Scenario: User rents apartment on airbnb website
      When User selects region "Southeast Asia" or types a location ""
      And User selects check-in date "02/16/2023" and check-out date "04/16/2023"
      And User adds guests, adults "2" child "2", infants "1" and pets "1"
      And User clicks search button

