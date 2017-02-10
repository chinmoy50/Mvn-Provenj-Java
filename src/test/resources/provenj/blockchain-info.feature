# features/build-enclosure.feature
Feature: Get info on public blockchains

  Scenario: Fetch the most recent Bitcoin block number
    When I request the most recent Bitcoin block number
    Then it should be greater than 452422

  Scenario: Fetch the most recent Bitcoin block hash
    When I request the most recent Bitcoin block hash 
    Then it should be 64 characters long

  Scenario: Fetch the most recent Ethereum block number
    When I request the most recent Ethereum block number
    Then it should be greater than 3158840

  Scenario: Fetch the most recent Ethereum block hash
    When I request the most recent Ethereum block hash 
    Then it should be 66 characters long
