# features/gather-ethereum-data.feature
Feature: Collect Ethereum block info to prove earliest possible creation date

Scenario: When creating new data the Ethereum blockchain info is needed
  Given the current time
  When called
  Then Ethereum.HighestBlockNumber should be greater than 1
    and Ethereum.LastBlockHash should be a hash
