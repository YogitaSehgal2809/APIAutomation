#Test Suite
Feature: Validating place APIs
#  Test case
#  When performing parametrization, we need to use scenario outline instead of scenario
  @AddPlace
  Scenario Outline: Verify if place is being successfully added through AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
#    "" allows us to reuse the parameter
    When User calls "AddPlaceAPI" API with "Post" http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
    #    parametrizing test
    Examples:
      | name | language | address |
      | Jatin | Hindi | #764, New Hamida Colony |
   #   | Yogita | English | #42, East Bhatia Nagar |

  @DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" API with "Post" http request
    Then API call is success with status code 200
    And "status" in response body is "OK"


