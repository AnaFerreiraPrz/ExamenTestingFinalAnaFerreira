package frontend;

import DigitalBooking.HomePage;
import DigitalBooking.RegisterPage;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static reportes.ReportFactory.captureScreenshot;

@Tag("LOGIN")
public class TestRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Test-Register.html");
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
    }

    @Test
    @Tag("EXITOSO")
    public void testRegister() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        HomePage homePage = new HomePage(driver, wait);
        homePage.clickRegister();
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.fillForm(  "firstNameValue",
                    "lastNameValue",
                    "addressValue",
                    "cityValue",
                    "stateValue",
                    "zipCodeValue",
                    "phoneValue",
                    "ssnValue",
                    generateRandomString(10),
                    "passwordValue",
                    "passwordValue");
            test.log(Status.PASS, "Ingreso todos los datos del Registro");
            registerPage.clickRegister();

            if (registerPage.cuentaCreadaGracias().equals("Your account was created successfully. You are now logged in.")) {
                test.log(Status.PASS, "Usuario registrado");
            } else {
                test.log(Status.FAIL, "Fallo registro de usuario");
            }

            test.log(Status.PASS, "Validación de Registro Exitoso");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE REGISTRO" + error.getMessage());
            captureScreenshot(test, "FAIL_RegistroExitoso", driver);
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

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return result.toString();
    }
}
