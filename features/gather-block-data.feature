# features/gather-block-data.feature
Feature: Collect blockchain information to prove earliest possible creation date

Scenario: When creating new data the blockchain info is needed
  Given the current time
  When called
  Then Blockchain.HighestBlockNumber should be greater than 1
    and Blockchain.LastBlockHash should be a hash
