Feature: Error message for special character

Scenario Outline: Validate invalid(special character vehicle id) in Dealer portal
Given user is on dealer portal
When user enters <vehicle id> in ENTER REG
And clicks on Find vehicle
Then Please enter a valid car registration message should be displayed

Examples:
|vehicle id|
|&*&(**(*7hjhk&h |