package stepDefinitions;

import instruments.Instruments;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Stepdef_tradeDetails {
	Instruments instruments = new Instruments();

	@Given("^Console displays to input the dates and then user enters the date$")
	public void console_displays_to_input_the_dates_and_then_user_enters_the_date()
			throws Throwable {
		instruments.getDates();

	}

	@Then("^Display the instrument wise traded volume between the date range$")
	public void display_the_instrument_wise_traded_volume_between_the_date_range()
			throws Throwable {
		instruments.getInstrumentWiseTradedVolume();

	}

	@And("^Display the List of instrument not traded between the dates$")
	public void display_the_List_of_instrument_not_traded_between_the_dates()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		instruments.getIntrumentsNotTraded();
	}

}