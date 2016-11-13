# features/query-ethereum-status.feature
Feature: Check the status of a submission to Ethereum

Scenario: User checks the status of a picture successfuly submitted to Ethereum
  Given an IPFS hash
  When we query the Proven contract on Ethereum
  Then we should be returned a 

Scenario: User checks the status of a picture never submitted to Ethereum
  Given an IPFS hash
  When we query the Proven contract on Ethereum
  Then we should get a message indicating that it was not found
