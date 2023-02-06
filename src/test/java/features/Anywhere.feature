Feature:Finding apartment anywhere
  Using this search option user should be able to search apartments anywhere in the world
  Background:User should be on airbnb website
    Given User navigate to airbnb website
    @AnywhereRegionStays
    Scenario: User rents apartment on airbnb website
      When User selects region "Southeast Asia" or types a location "Bordeaux, France"
      And User selects check-in date "Apr 28 2023" and check-out date "May 7 2023"
      And User adds guests, adults "2" child "0", infants "0" and pets "0"
      And User clicks search button

