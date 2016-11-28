# features/gather-ethereum-data.feature
Feature: Collect Ethereum block info to prove earliest possible creation date

Scenario: Need the most recent Ethereum block number
  Given the current time and a connection to the internet
  When asked for the most recent Ethereum block number
  Then returned value should be greater than 0

Scenario: Need the hash of the most recent Ethereum block
  Given the current time and a connection to the internet
  When asked for the most recent Ethereum block hash
  Then the returned value should be a valid Ethereum block hash
