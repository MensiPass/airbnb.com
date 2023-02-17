Feature:Filtering apartments with detail data
  Using filters popup window user can perform detail search of apartments with specific data
  Background:User should be on airbnb.com website
    Given User goes to airbnb.com website and clicks filter button
  @DetailFilter
  Scenario:User performs detail search with filters option
    When User specifies min price "50" and max price "660"
    And User specifies the type "Entire place" of the place
    And User specifies number of rooms "1", number of beds "1" and number of bathrooms "1"
    And User selects property type "House"
    And User selects amenities: essentials "Wifi", features "Free parking on premises", location "Beachfront" and safety "Smoke alarm"
    And User selects booking options: instant book "Yes" and self-check "Yes"
    And User selects accessibility features: guest entrance and parking "", bedroom "", bathroom "Shower grab bar" and adaptive equipment ""
    And User selects top tier stays superhost "Yes" and airbnb plus "No"
    And User selects host language "English"
    And User clicks show homes button to  search apartments for specified data
