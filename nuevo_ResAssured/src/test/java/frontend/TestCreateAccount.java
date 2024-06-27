package frontend;

import DigitalBooking.CreateAccountPage;
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
public class TestCreateAccount {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Test-CreateAccount.html");
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
        homePage.clickOpenAccount();
    }

    @Test
    @Tag("EXITOSO")
    public void testOpenAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Abrir Cuenta Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, wait);

        try {
            createAccountPage.selectAccountTypeSavings();
            createAccountPage.clickOpenAccountButton();

            if (createAccountPage.successCreatedAccount().equals("Congratulations, your account is now open.")) {
                test.log(Status.PASS, "Cuenta creada");
            } else {
                test.log(Status.FAIL, "Fallo crear cuenta");
            }

            test.log(Status.PASS, "Validación de crear cuenta Exitoso");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE CREAR CUENTA" + error.getMessage());
            captureScreenshot(test, "FAIL_CrearCuentaExitoso", driver);
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
