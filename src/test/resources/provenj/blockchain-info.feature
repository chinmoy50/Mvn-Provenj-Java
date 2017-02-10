# features/build-enclosure.feature
Feature: Get info on public blockchains

  Scenario: Fetch the most recent Bitcoin block number
    When I request the most recent Bitcoin block number
    Then the Bitcoin block number should be greater than 452422

  Scenario: Fetch the most recent Bitcoin block hash
    When I request the most recent Bitcoin block hash 
    Then the Bitcoin block hash should be 64 characters long

  Scenario: Fetch the most recent Ethereum block number
    When I request the most recent Ethereum block number
    Then the Ethereum block number should be greater than 3158840

  Scenario: Fetch the most recent Ethereum block hash
    When I request the most recent Ethereum block hash 
    Then the Ethereum block hash should be 66 characters long
