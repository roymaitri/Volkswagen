Feature: Vehicle exists

Scenario Outline: Validate vehicle exists in Dealer portal
Given user is on dealer portal
When user enters <vehicle id> in ENTER REG
And clicks on Find vehicle
Then user should see <vehicle id> for that provided registration number
And cover end date should greater than cover start date 

Examples:
|vehicle id|
|OV12UYY   |