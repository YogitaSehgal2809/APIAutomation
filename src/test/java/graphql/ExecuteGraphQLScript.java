package graphql;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ExecuteGraphQLScript {
    public static void main(String[] args){
//Query
        String queryResponse =given().log().all().contentType("application/json").
//                Restassured expects to send query in JSON, we need to take from view source
//        We can add values to variables externally using local variables, data providers
                body("{\"query\":\"query ($characterId: Int!, $locationId: Int!, $episodeId: Int!, $name: String, $episode: String, $episodeName: String) {\\n  character(characterId: $characterId) {\\n    id\\n    name\\n    type\\n    status\\n    species\\n    gender\\n    image\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    id\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: $name}) {\\n    info {\\n      count\\n    }\\n    result {\\n      id\\n    }\\n  }\\n  episodes(filters: {episode: $episode, name: $episodeName}) {\\n    result {\\n      name\\n      air_date\\n      id\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":13151,\"locationId\":19106,\"episodeId\":13454,\"name\":\"Yogita\",\"episode\":\"12\",\"episodeName\":\"Episode 1\"}}").
                when().post("https://rahulshettyacademy.com/gq/graphql").
                then().extract().response().asString();
        System.out.println("Query Response>>");
        System.out.println(queryResponse);
//        We can parse response using JSON path or POJO classes
        JsonPath js1=new JsonPath(queryResponse);
        String characterName=js1.getString("data.character.name");
        Assert.assertEquals(characterName,"Yogita");


//        Mutation
        String mutationResponse =given().log().all().contentType("application/json").
//                Restassured expects to send query in JSON, we need to take from view source
//        We can add values to variables externally using local variables, data providers
        body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$EpisodeName:String! ) {\\n  createLocation(location: {name: $locationName, type: \\\"Asia\\\", dimension: \\\"23\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Villain\\\", status: \\\"alive\\\", species: \\\"human\\\", gender: \\\"female\\\", image: \\\"abc.jpg\\\", originId: 19105, locationId: 19105}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $EpisodeName, air_date: \\\"13 Mar 2025\\\", episode: \\\"12\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds:[19188]){\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Austin,Texas\",\"characterName\":\"Ian Somerhalder\",\"EpisodeName\":\"Vampires Diaries S1 Ep1\"}}").
                when().post("https://rahulshettyacademy.com/gq/graphql").
                then().extract().response().asString();
        System.out.println("Mutation Response>>");
        System.out.println(mutationResponse);


    }

}
