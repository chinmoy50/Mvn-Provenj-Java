# features/gather-ethereum-data.feature
Feature: Collect Bitcoin block info to prove earliest possible creation date

Scenario: Need the most recent Bitcoin block number when taking a picture
  Given the current time and a connection to the internet
  When called
  Then returned value should be greater than 0

Scenario: Need the hash of the most recent Bitcoin block when taking a picture
  Given the current time and a connection to the internet
  When called
  Then the returned value should be a valid Bitcoin block hash

Scenario: Need the most recent Bitcoin block number immediately
  Given the current time
  When called
  Then returned value should be greater than 0

Scenario: Need the most recent Bitcoin block hash immediately
  Given the current time
  When called
  Then the returned value should be a valid Bitcoin block hash
