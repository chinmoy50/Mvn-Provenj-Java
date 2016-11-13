# features/build-manifest.feature
Feature: Build a manifest of the files supplied to Proven

  Scenario: A picture needs to be listed in the manifest
    Given a JPEG file named "IMG_00001.jpeg"
    And the current Bitcoin block number btc_block
    And the current Bitcoin block hash btc_hash
    And the current Ethereum block number eth_block
    And the current Ethereum block hash eth_hash
    And the IPFS hash from the last submitted file hash_ipfs
    And the other hashes from the last submitted file file_hashes
    And the GUID for the submission
    When I ask for a manifest file
    Then manifest.FileName should equal "IMG_00001.jpeg"
    And manifest.BitcoinBlockNumber should match btc_block
    And manifest.BitcoinLastBlockHash should equal btc_hash
    And manifest.EthereumBlockNumber should equal eth_block
    And manifest.EthereumLastBlockHash should equal eth_hash
    And manifest.PreviousIFPSHash should equal hash_ipfs
    And manifest.FileHashes should equal file_hashes
    And manifest.GUID should equal GUID
