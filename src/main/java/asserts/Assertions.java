package asserts;

import fileUtils.FileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.asserts.Assertion;

import java.io.File;

public class Assertions extends Assertion {
    public static void assertJsonResponseSchema(Response response, String fileName){
        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FileReader.getInstance().SCHEMA_PATH + fileName)));
    }

}
