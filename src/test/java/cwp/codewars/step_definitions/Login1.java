package cwp.codewars.step_definitions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;


import cwp.codewars.utilities.Driver;
import cwp.codewars.utilities.waits;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login1 {
    @Given("user is on  home page")
    public void user_is_on_home_page() throws Throwable {
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get("https://www.codewars.com/");

    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() throws Throwable {
        // Driver.getDriver().findElement(By.cssSelector("[id*='search-query']")).sendKeys(search + Keys.ENTER);
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement loginLink= Driver.getDriver().findElement(By.xpath("//*[@id='header_section']/ul/li[3]/a"));
        loginLink.click();

        WebElement emailInput= Driver.getDriver().findElement(By.id("user_email"));
        emailInput.sendKeys("benchprep@sdet.com");

        WebElement passwordInput=  Driver.getDriver().findElement(By.id("user_password"));
        passwordInput.sendKeys("c0d3Ch@llenge21");

        WebElement signInButton= Driver.getDriver().findElement(By.xpath("//*[@id=\"new_user\"]/button[2]"));
        signInButton.click();
    }
    @When("user search for {string}")
    public void userSearchFor(String arg0) {
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchBox=Driver.getDriver().findElement(By.xpath("(//div[@class='my-0.5'])[1]"));
        searchBox.click();
        WebElement searchInput=Driver.getDriver().findElement(By.id("search-input"));
        searchInput.sendKeys(arg0);
        WebElement search=Driver.getDriver().findElement(By.xpath("//button[@id='search']"));
        search.click();
    }


    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() throws Throwable {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }


    @Then("user should see results")
    public void user_should_see_results() throws Throwable {

        String actualTitle= Driver.getDriver().getCurrentUrl();
        String expectedTitle="arrays";
        Assert.assertTrue( actualTitle.contains(expectedTitle));
        /*
        if(actualTitle.contains((expectedTitle))){
            System.out.println("Title contains search term.PASSED");
        }else{
            System.out.println("Title does not contain search term.Failed!");
        }

         */
    }
    @When("user clicks training set up icon to navigate Training SetUp")
    public void user_clicks_training_icon(){
        WebElement trainingIcon=Driver.getDriver().findElement(By.xpath("(//a[@href='/trainer/setup'])[2]"));
        trainingIcon.click();
    }
    @When("user picks Java for training")
    public void user_picks_for_training() {
        waits waits = new waits();

        WebElement javaIcon=Driver.getDriver().findElement(By.xpath("//i[@class='icon-moon-java ']"));
        waits.waitForStaleElement(javaIcon);
        javaIcon.click();
    }

    @Then("user clicks to blue save button to navigate back to dashboard")
    public void user_clicks_blue_save_button(){
        WebElement saveButton=Driver.getDriver().findElement(By.xpath("//a[@class='btn mt-4 js-save']"));
        saveButton.click();

    }


    @Then("verify user can see that language at dropdown menu")
    public void user_should_see_that_language_at_dropdown_menu() throws InterruptedException {

        WebElement dropDown=Driver.getDriver().findElement(By.xpath("//*[@id='trainer_language']"));
        dropDown.click();
        System.out.println("dropDown.isDisplayed() = " + dropDown.isDisplayed());
        System.out.println("dropDown.getText() = " + dropDown.getText());
        verifyWord(dropDown,"java");
        Thread.sleep(2000);
        WebElement trainingIcon=Driver.getDriver().findElement(By.xpath("(//a[@href='/trainer/setup'])[2]"));
        trainingIcon.click();
        Thread.sleep(2000);

        try{
            WebElement javaIcon = Driver.getDriver().findElement(By.xpath("//i[@class='icon-moon-java ']"));
            javaIcon.click();
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        WebElement saveButton=Driver.getDriver().findElement(By.xpath("//a[@class='btn mt-4 js-save']"));
        saveButton.click();


    }
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void verifyWord(WebElement dropDown,String expectedWord){
        String [] dropDownArr= dropDown.getText().split("\n");
        System.out.println(Arrays.toString(dropDownArr));
        List<String> actualTexts=new ArrayList<>();
        for(String each:dropDownArr){
            try{
                if(each.equalsIgnoreCase(expectedWord)){
                    System.out.println(each);
                }}catch(Exception e){
                System.out.println("invalid action");
            }
        }
    }

    @When("the user logs in using {string} and {string}")
    public void the_user_logs_in_using(String email, String password) {

        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement loginLink= Driver.getDriver().findElement(By.xpath("//*[@id='header_section']/ul/li[3]/a"));
        loginLink.click();

        WebElement emailInput= Driver.getDriver().findElement(By.id("user_email"));
        emailInput.sendKeys(email);

        WebElement passwordInput=  Driver.getDriver().findElement(By.id("user_password"));
        passwordInput.sendKeys(password);

        WebElement signInButton= Driver.getDriver().findElement(By.xpath("//*[@id=\"new_user\"]/button[2]"));
        signInButton.click();
    }



    @Then("the user should not be able to login")
    public void the_user_should_not_be_able_to_login() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        WebElement loginButton=Driver.getDriver().findElement(By.xpath("//div[@class='alert-box error flash-msg error']"));
        String actualText= loginButton.getText();
        String expectedText="Invalid Email or password.";
        Assert.assertEquals(expectedText,actualText);

    }

    public static List<String> getElementsText(List<WebElement>list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    @After("")
    public void tearDown (Scenario scenario){
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", scenario.getName());
        }
       Driver.closeDriver();
    }


}
/*
Visit www.codewars.com.
Use username and password to login
benchprep@sdet.com
c0d3Ch@llenge21
Automate the following scenarios:
Ability to login successfully and verify it
Ability to complete a search and verify it
Ability to enroll in a course and verify it
Choose a scenario to write a negative test against
Demonstrate the ability to write reusable code and how to handle data management for tests by being able to pass in dynamic values
 */
