package resources;

import pojo.AddPlacePayload;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;
//test data will be built here
public class TestDataBuild {

    public AddPlacePayload addPlacePayload(String name, String language, String address){
        AddPlacePayload p=new AddPlacePayload();
        p.setAccuracy(56);
        p.setAddress(address);
        p.setPhone_number("9068054668");
        p.setLanguage(language);
        p.setName(name);
        p.setWebsite("http://google.com");
        List<String> l=new ArrayList<String>();
        l.add("type 3");
        l.add("type 4");
        p.setTypes(l);
        Location location=new Location();
        location.setLat(29.9);
        location.setLng(32.6);
        p.setLocation(location);
        return p;
    }

    public String DeletePlacePayload(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}";
    }
}
