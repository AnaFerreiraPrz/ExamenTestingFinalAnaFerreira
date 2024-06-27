package frontend;

import DigitalBooking.AccountOverviewPage;
import DigitalBooking.HomePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static reportes.ReportFactory.captureScreenshot;

@Tag("LOGIN")
public class TestAccountSummary {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Test-AccountSummary.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void onBeforeEach() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        HomePage homePage = new HomePage(driver, wait);
        homePage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        homePage.login();
        homePage.clickAccountsOverview();
    }

    @Test
    @Tag("EXITOSO")
    public void testOpenAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Resumen de Cuenta Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);

        try {

            if (accountOverviewPage.successAccountOverview().equals("*El saldo incluye depósitos que pueden estar sujetos a retenciones")) {
                test.log(Status.PASS, "Resumen de cuentas correcta");
            } else {
                test.log(Status.FAIL, "Fallo Resumen de cuentas");
            }

            test.log(Status.PASS, "Validación de Resumen de cuentas Exitoso");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE RESUMEN DE CUENTAS" + error.getMessage());
            captureScreenshot(test, "FAIL_ResumenCuentaExitoso", driver);
        }
        test.log(Status.INFO, "Finaliza el Test");
    }

    @AfterEach
    public void endTest() throws InterruptedException {
        driver.close();
    }

    @AfterAll
    public static void finish() {
        extent.flush();
    }

}
