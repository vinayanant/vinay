package stepDefinitions;

import instruments.Instruments;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef_tradeDetails {
	Instruments instruments = new Instruments();

	@Given("^Application will run and ask you to input the dates$")
	public void application_will_run_and_ask_you_to_input_the_dates()
			throws Throwable {
        instruments.readTextFiles();
	}

	@When("^Capture the from date and to date from the user$")
	public void capture_the_from_date_and_to_date_from_the_user()
			throws Throwable {
		
	}

	@Then("^Display the instrument wise traded volume between the date range$")
	public void display_the_instrument_wise_traded_volume_between_the_date_range()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("^Display the List of instrument not traded between the dates$")
	public void display_the_List_of_instrument_not_traded_between_the_dates()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

}