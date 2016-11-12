# features/gather-ethereum-data.feature
Feature: Collect Bitcoin block info to prove earliest possible creation date

Scenario: When creating new data the Bitcoin blockchain info is needed
  Given the current time
  When called
  Then Bitcoin.HighestBlockNumber should be greater than 1
    and Bitcoin.LastBlockHash should be a hash
