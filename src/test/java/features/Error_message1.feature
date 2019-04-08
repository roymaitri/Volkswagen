Feature: Negative test for alphanumeric vehicle id

Scenario Outline: Validate invalid(alphanumeric vehicle id) in Dealer portal
Given user is on dealer portal
When user enters <vehicle id> in ENTER REG
And clicks on Find vehicle
Then Sorry record not found message should be displayed

Examples:
|vehicle id|
|78hkkhkh  |