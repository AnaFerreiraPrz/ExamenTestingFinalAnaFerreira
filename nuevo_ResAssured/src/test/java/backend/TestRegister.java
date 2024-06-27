package backend;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonObject;
import dev.failsafe.internal.util.Assert;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class TestRegister {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/API-Register.html");
    static ExtentReports extent;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }

    @Test
    @Tag("POST")
    public void Test01() {
        ExtentTest test = extent.createTest("Test Registro");
        test.log(Status.INFO, "Comienza el Test");

        given()
                .contentType("multipart/form-data")
                .multiPart("customer.firstName","1")
                .multiPart("customer.lastName","1")
                .multiPart("customer.address.street","1")
                .multiPart("customer.address.city","1")
                .multiPart("customer.address.state","1")
                .multiPart("customer.address.zipCode","1")
                .multiPart("customer.phoneNumber","1")
                .multiPart("customer.ssn","1")
                .multiPart("customer.username",generateRandomString(10))
                .multiPart("customer.password","1")
                .multiPart("repeatedPassword","1")
                .when().post("https://parabank.parasoft.com/parabank/register.htm")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        test.log(Status.PASS, "Test finalizado");
    }

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return result.toString();
    }

}