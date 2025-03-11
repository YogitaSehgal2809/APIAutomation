package stepdefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition s = new StepDefinition();
        if (StepDefinition.placeId == null) {
            s.add_place_payload_with("Yogita", "Spanish", "Model Colony, Yamunanagar");
            s.user_calls_api_with_post_http_request("AddPlaceAPI", "POST");
            s.verify_place_id_created_maps_to_using("Yogita", "GetPlaceAPI");
        }
    }
}
