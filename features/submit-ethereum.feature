# features/submit-ipfs.feature
Feature: Submit an IPFS payload to Ethereum

Scenario: User takes a picture we created everything need to record in Ethereum 
  Given a set of cryptographic hashes
    and an IPFS hash
    and the private key to an Ethereum wallet
  When we create a signed transaction and send it to Ethereum
  Then they should be submitted to the mempool for a subsequent block
