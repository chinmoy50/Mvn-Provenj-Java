# features/build-manifest.feature
Feature: Build a manifest of the files supplied to Proven

  Scenario: A picture needs to be listed in the manifest
    Given a JPEG file named IMG_00001.jpeg
      and the current Bitcoin block number btc_block
      and the current Bitcoin block hash btc_hash
      and the current Ethereum block number eth_block
      and the current Ethereum block hash eth_hash
      and the IPFS hash from the last submitted file hash_ipfs
      and the other hashes from the last submitted file file_hashes
      and the GUID for the submission
    When I ask for a manifest file
    Then manifest.BitcoinBlockNumber should match btc_block
      and manifest.BitcoinLastBlockHash should equal btc_hash
      and manifest.EthereumBlockNumber should equal eth_block
      and manifest.EthereumLastBlockHash should equal eth_hash
      and manifest.PreviousIFPSHash should equal hash_ipfs
      and manifest.FileHashes should equal file_hashes
      and manifest.GUID should equal GUID
