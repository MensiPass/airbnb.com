Feature:Filtering apartments with detail data
  Using filters popup window user can perform detail search of apartments with specific data
  Background:User should be on airbnb.com website
    Given User goes to airbnb.com website and clicks filter button
  @DetailFilter
  Scenario:User performs detail search with filters option
    When User specifies min price "250" and max price "500"
    And User specifies the type "Shared room" of the place
    And User specifies number of rooms "Any", number of beds "2" and number of bathrooms "1"
    And User selects property type "Guesthouse"
    And User selects amenities: essentials "Dryer", features "Gym", location "Waterfront" and safety "Smoke alarm"
    And User selects booking options: instant book "No" and self-check "Yes"
    And User selects accessibility features: guest entrance and parking "", bedroom "", bathroom "" and adaptive equipment ""
    And User selects top tier stays superhost "" and airbnb plus ""
    And User selects host language ""
    And User clicks show homes button to  search apartments for specified data
