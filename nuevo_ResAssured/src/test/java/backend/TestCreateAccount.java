package backend;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;

public class TestCreateAccount {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/API-CreateAccount.html");
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
        ExtentTest test = extent.createTest("Test Crear Cuenta");
        test.log(Status.INFO, "Comienza el Test");
        String customerId = "12434";
        String accountType = "0";
        String fromAccountId = "13566";
        given()
                .when()
                .auth().preemptive().basic("a", "a")
                .contentType("application/json")
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=" + customerId +
                        "&newAccountType=" + accountType + "&fromAccountId=" + fromAccountId)
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        test.log(Status.PASS, "Test finalizado");
    }

}