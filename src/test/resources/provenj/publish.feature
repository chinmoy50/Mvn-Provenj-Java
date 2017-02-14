# features/publish.feature
Feature: Publish IPFS hash to public blockchain

  Scenario: Using a gateway
    When I submit to the gateway the hash "QmNjDm89By6jQRNwJ2idbCMWKzew2HAXHhbYKxK6Bn5VoW"
    Then something should happen
