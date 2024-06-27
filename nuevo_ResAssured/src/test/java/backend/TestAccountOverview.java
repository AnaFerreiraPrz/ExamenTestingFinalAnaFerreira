package backend;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;

public class TestAccountOverview {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/API-AccountOverview.html");
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
        ExtentTest test = extent.createTest("Test Obtener Cuenta");
        test.log(Status.INFO, "Comienza el Test");
        String customerId = "12434";

        given()
                .when()
                .auth().preemptive().basic("a", "a")
                .get("http://parabank.parasoft.com/parabank/services/bank/customers/"+customerId+"/accounts")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();
        
        test.log(Status.PASS, "Test finalizado");
    }

}