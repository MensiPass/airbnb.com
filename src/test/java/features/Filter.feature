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

  @DetailFilterOutline
  Scenario Outline:User performs detail search with filters option
    When User specifies min price "<min-price>" and max price "<max-price>"
    And User specifies the type "<place-type>" of the place
    And User specifies number of rooms "<rooms>", number of beds "<beds>" and number of bathrooms "<baths>"
    And User selects property type "<property-type>"
    And User selects amenities: essentials "<amenities-essentials>", features "<amenities-features>", location "<amenities-location>" and safety "<amenities-safety>"
    And User selects booking options: instant book "<instant-book>" and self-check "<self-check>"
    And User selects accessibility features: guest entrance and parking "<acc-entrance>", bedroom "<acc-bedrooms>", bathroom "<acc-bathroom>" and adaptive equipment "<acc-eqipment>"
    And User selects top tier stays superhost "<superhost>" and airbnb plus "<plus>"
    And User selects host language "<lang>"
    And User clicks show homes button to  search apartments for specified data

    Examples:
      | min-price | max-price | place-type                | rooms | beds | baths | property-type | amenities-essentials | amenities-features       | amenities-location    | amenities-safety                  | instant-book | self-check | acc-entrance                                     | acc-bedrooms                                                   | acc-bathroom                              | acc-eqipment            | superhost | plus | lang           |
      | 30        |           | Entire place              | 1     | 1    | 1     | House         | Wifi                 | Free parking on premises |                       | Smoke alarm                       | Yes          | No         | Step-free guest entrance                         |                                                                | Shower grab bar                           |                         | Yes       | No   | English        |
      | 50        | 20000     | Entire place,Private room | 1     | 1    | 1     | House,Hotel   | Wifi,Kitchen         | Pool,Gym                 | Beachfront,Waterfront | Smoke alarm,Carbon monoxide alarm | No           | No         | Step-free guest entrance,Accessible parking spot | Step-free bedroom access,Bedroom entrance wider than 32 inches | Step-free bathroom access,Shower grab bar | Ceiling or mobile hoist | No        | No   | English        |
      | 35        | 50000     | Private room              | 1     | 2    | 1     | Hotel         | Wifi                 | Breakfast                |                       |                                   | Yes          | No         |                                                  |                                                                |                                           |                         | Yes       | No   | English,French |


