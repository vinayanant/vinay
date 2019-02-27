Feature: Application to fetch instrument wise traded and not traded volume

  Scenario: Fetch the instrument wise traded volume between the date range and list of instrument not traded between the dates
    Given Console displays to input the dates and then user enters the date
    Then Display the instrument wise traded volume between the date range
    And Display the List of instrument not traded between the dates
