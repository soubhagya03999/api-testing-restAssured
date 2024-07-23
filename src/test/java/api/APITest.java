package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.*;


public class APITest {
    
    @Test(description = "get requrest")
    public void TestCase01(){
        Response res= RestAssured.get("https://reqres.in/api/users/2");
        System.out.println(res.asString());
        System.out.println("status code:"+res.getStatusCode());
    }

    @Test(description = "get request with assert")
    public void TestCase02(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Response code: "+response.statusCode());
        System.out.println("Response body: "+response.getBody().asString());
        System.out.println("Response time: "+response.getTime());
        System.out.println("Response headers: "+response.getHeader("Content-Type"));
        int expectedStatusCode=200;
        int actualStatusCode=response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Test(description = "get request with BDD--behavior driven devlopment model")
    public void TestCase03(){
        RestAssured.baseURI="https://reqres.in/api/users";
        RestAssured.given().queryParam("pages", 2)
        .when().get()
        .then().statusCode(200);
    }

    @Test(description = "post method")
    public void TestCase04(){
        JSONObject js = new JSONObject();
        js.put("name", "morpheus");
        js.put("job", "leader");
        
        RestAssured.baseURI="https://reqres.in/api/users";
        RestAssured.given().header("Content-type","application/json")
        .contentType(ContentType.JSON).body(js.toString())
        .when().post()
        .then().statusCode(201).log().all();
    }

    @Test(description = "put method")
    public void TestCase05(){
        JSONObject json = new JSONObject();
        json.put("name", "ironman");
        json.put("job", "avenjure");

        RestAssured.baseURI ="https://reqres.in/api/users/2";
        RestAssured.given().header("Content-type","application/json")
        .contentType(ContentType.JSON)
        .body(json.toString())
        .when().put()
        .then().statusCode(200)
        .log().all();
    }

    @Test(description = "patch method---partially update fungtionality")
    public void TestCase06(){
        JSONObject json = new JSONObject();
        json.put("name", "Neeraj");
        json.put("job", "Tester");

        RestAssured.baseURI ="https://reqres.in/api/users/2";
        RestAssured
        .given()
            .header("Content-type","application/json")
            .contentType(ContentType.JSON)
            .body(json.toString())
        .when()
            .patch()
        .then()
            .statusCode(200)
        .log()
            .all();
    }

    @Test
    public void TestCase07(){
        RestAssured.baseURI="https://reqres.in/api/users/2";
        RestAssured
        .given()
        .when()
            .delete()
        .then()
        .log()
            .all();
    }
}
