package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

//reusable methods will be defined here
public class Utils {
//    In a single execution, this requestspecification will be used across the entire test cycle
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
//        Capture logs and write into file
//        to call methods in same class, we can make them static
//        Its common to all the tests that will be executed, it will be created once, it will help us to log details across multiple tests, for every new test
//        a new log file will not be created, for the entire test life cycle only 1 log file will be created
        if(req==null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setRelaxedHTTPSValidation().
                    addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

//    extract value from global.properties
    public static String getGlobalValue(String key) throws IOException {
//        Using properties class we can scan any property file
        Properties prop=new Properties();
//        Read properties file
        FileInputStream propFile=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//Global.properties");
        prop.load(propFile);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String keyValue){
        String stringResponse= response.asString();
        JsonPath js=new JsonPath(stringResponse);
        String value=js.get(keyValue).toString();
        return value;
    }
}
