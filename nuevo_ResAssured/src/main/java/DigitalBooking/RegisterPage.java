package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");
    private By buttonRegister = By.xpath("/html/body/div[1]/div[3]/div[2]/form/table/tbody/tr[13]/td[2]/input");
    private By successRegister = By.linkText("Your account was created successfully. You are now logged in.");

    /**
     * Constructor de la clase RegisterPage
     *
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickRegister() throws InterruptedException {
        this.click(buttonRegister);
    }

    public void fillForm(String firstNameValue,
                         String lastNameValue,
                         String addressValue,
                         String cityValue,
                         String stateValue,
                         String zipCodeValue,
                         String phoneValue,
                         String ssnValue,
                         String usernameValue,
                         String passwordValue,
                         String confirmPasswordValue
                         ) throws InterruptedException {
        this.sendText(firstNameValue, firstName);
        this.sendText(lastNameValue, lastName);
        this.sendText(addressValue, address);
        this.sendText(cityValue, city);
        this.sendText(stateValue, state);
        this.sendText(zipCodeValue, zipCode);
        this.sendText(phoneValue, phone);
        this.sendText(ssnValue, ssn);
        this.sendText(usernameValue, username);
        this.sendText(passwordValue, password);
        this.sendText(confirmPasswordValue, confirmPassword);
    }



    public String cuentaCreadaGracias() throws InterruptedException {
        return this.getText(successRegister);
    }


}
