package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage extends BasePage {

    private By successAccountOverview = By.linkText("*El saldo incluye depósitos que pueden estar sujetos a retenciones");
    private By accountActivityText = By.linkText("*El saldo incluye depósitos que pueden estar sujetos a retenciones");
    public AccountOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public String successAccountOverview() throws InterruptedException {
        return this.getText(successAccountOverview);
    }

    public String accountActivityText() throws InterruptedException {
        return this.getText(accountActivityText);
    }


}
