#Test Suite
Feature: Validating place APIs
#  Test case
  Scenario: Verify if place is being successfully added through AddPlace API
    Given Add Place Payload is available
    When User calls "AddPlace" API with Post http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"