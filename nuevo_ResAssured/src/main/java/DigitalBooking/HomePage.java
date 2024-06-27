package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private By buttonRegister = By.xpath("/html/body/div[1]/div[3]/div[1]/div/p[2]/a");

    private By loginUser = By.xpath("/html/body/div[1]/div[3]/div[1]/div/form/div[1]/input");
    private By loginPassword = By.xpath("/html/body/div[1]/div[3]/div[1]/div/form/div[2]/input");
    private By buttonLogin = By.xpath("/html/body/div[1]/div[3]/div[1]/div/form/div[3]/input");
    private By openAccount = By.xpath("/html/body/div[1]/div[3]/div[1]/ul/li[1]/a");
    private By buttonAccountsOverview = By.xpath("/html/body/div[1]/div[3]/div[1]/ul/li[2]/a");
    private By buttonTransferFunds = By.xpath("/html/body/div[1]/div[3]/div[1]/ul/li[3]/a");


    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickRegister() throws InterruptedException {
        this.click(buttonRegister);
    }

    public void login() throws InterruptedException {
        //Login con un usuario previamente creado
        this.sendText("asd", loginUser);
        this.sendText("asd", loginPassword);
        this.click(buttonLogin);
    }

    public void clickOpenAccount() throws InterruptedException {
        this.click(openAccount);
    }

    public void clickAccountsOverview() throws InterruptedException {
        this.click(buttonAccountsOverview);
    }

    public void clickTransferFunds() throws InterruptedException {
        this.click(buttonTransferFunds);
    }

}
