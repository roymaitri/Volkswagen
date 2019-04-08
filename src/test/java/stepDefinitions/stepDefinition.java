package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class stepDefinition {

	static WebDriver mDriver = null;
	private static final int mTimeout = 2; // 2 seconds

	@Given("^user is on dealer portal$")
	public void user_is_on_dealer_portal() {
		if (mDriver == null) {
			mDriver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
			mDriver.get("https://covercheck.vwfsinsuranceportal.co.uk/");
		}
	}

	@When("^user enters (.+) in ENTER REG$")
	public void user_enters_in_enter_reg(String vehicleid) {
		mDriver.findElement(By.id("vehicleReg")).clear();
		mDriver.findElement(By.id("vehicleReg")).sendKeys(vehicleid);
	}

	@And("^clicks on Find vehicle$")
	public void clicks_on_find_vehicle() {
		mDriver.findElement(By.cssSelector(".track-search")).click();
	}

	@Then("^user should see (.+) for that provided registration number$")
	public void user_should_see_result_for_that_provided_registration_number_only(String vehicleid) {
		By result = By.xpath("//div[@class='result']");
		String actual = new WebDriverWait(mDriver, mTimeout).
				until(ExpectedConditions.visibilityOfElementLocated(result)).getText();
		Assert.assertTrue("Expected Vehicle ID not matched with actual vehicle ID", actual.contains(vehicleid));
	}

	@And("^cover end date should greater than cover start date$")
	public void cover_end_date_should_greater_than_cover_start_date() throws ParseException {
		String cover_start = mDriver.findElement(By.xpath("//div[contains(text(),'Cover start:')]")).getText();
		String cover_end = mDriver.findElement(By.xpath("//div[contains(text(),'Cover end:')]")).getText();
		Date date1 = (Date) new SimpleDateFormat("dd MMM yyyy : hh : mm")
				.parse(cover_start.substring(cover_start.indexOf(':') + 2, cover_start.length()));
		Date date2 = (Date) new SimpleDateFormat("dd MMM yyyy : hh : mm")
				.parse(cover_end.substring(cover_end.indexOf(':') + 2, cover_start.length()));
		Assert.assertTrue("Cover end date is less than cover start date", date2.after(date1));
	}

    @Then("^Sorry record not found message should be displayed$")
    public void sorry_record_not_found_message_should_be_displayed() 
    {
		By result=By.xpath("//div[@class='result']");
    	String actual= new WebDriverWait(mDriver, mTimeout).
    			until(ExpectedConditions.visibilityOfElementLocated(result)).getText();
		Assert.assertTrue("Vehicle Id exists.",actual.contains("record not found"));
	}
    @Then("^Please enter a valid car registration message should be displayed$")
    public void please_enter_a_valid_car_registration_message_should_be_displayed()
    {
		By result=By.xpath("//div[@class='error-required']");
    	String actual= new WebDriverWait(mDriver, mTimeout).
    			until(ExpectedConditions.visibilityOfElementLocated(result)).getText();
		Assert.assertTrue("Vehicle Id exists.",actual.contains("enter a valid car registration"));
	}
  
}
