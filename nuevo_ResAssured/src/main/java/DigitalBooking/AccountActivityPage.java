package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountActivityPage extends BasePage {

    private By transferText = By.linkText("Transferir fondos");
    private By inputAmount = By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/p/input");
    private By originAccountDropdown = By.id("fromAccountId");
    private By destinationAccountDropdown = By.id("toAccountId");
    private By transferButton = By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/div[2]/input");
    private By transferCompleteText = By.linkText("¡Transferencia completa!");


    public AccountActivityPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public String getTransferText() throws InterruptedException {
        return this.getText(transferText);
    }

    public void inputAmount(String amount) throws InterruptedException {
        this.sendText(amount, inputAmount);
    }

    public void selectAccounts(String originAccount, String destinationAccount) throws InterruptedException {
        Select dropdownOrigin = new Select(this.findElement(originAccountDropdown));
        Select dropdownDestination = new Select(this.findElement(destinationAccountDropdown));
        dropdownOrigin.selectByValue(originAccount);
        dropdownDestination.selectByValue(destinationAccount);
    }

    public void clickTransferButton() throws InterruptedException {
        this.click(transferButton);
    }

    public String getTransferCompleteText() throws InterruptedException {
        return this.getText(transferCompleteText);
    }


}
