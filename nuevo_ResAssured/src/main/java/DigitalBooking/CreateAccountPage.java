package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage extends BasePage {

    private By accountTypeDropdown = By.id("type");
    private By openAccountButton = By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/div/input");
    private By successCreatedAccount = By.linkText("Congratulations, your account is now open.");

    public CreateAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }



    public void selectAccountTypeSavings() throws InterruptedException {
        Select dropdown = new Select(this.findElement(accountTypeDropdown));
        dropdown.selectByValue("1");
    }

    public void clickOpenAccountButton() throws InterruptedException {
        this.click(openAccountButton);
    }

    public String successCreatedAccount() throws InterruptedException {
        return this.getText(successCreatedAccount);
    }
}
