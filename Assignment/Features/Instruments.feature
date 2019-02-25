Feature: Application to read the trade files

  Scenario: Fetch the Instrument wise traded volume between the date range and list of instrument not traded between the dates
    Given Application will run and ask you to input the dates
    When Capture the from date and to date from the user
    Then Display the instrument wise traded volume between the date range
    And Display the List of instrument not traded between the dates