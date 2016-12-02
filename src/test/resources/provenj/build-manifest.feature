# features/build-manifest.feature
Feature: Build a manifest of the files supplied to Proven

  Scenario: A picture needs to be listed in the manifest
    Given a JPEG file named "IMG_00001.jpeg"
    And the current Bitcoin block number 438712
    And the current Bitcoin block hash "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And the current Ethereum block number 2619567
    And the current Ethereum block hash "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And the IPFS hash from the last submitted file "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    # TODO: multiple hashes. for now, SHA256
    And the other hashes for the file "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And the GUID for the submission "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
    When I ask for a manifest file
    Then manifest.FileName should equal "IMG_00001.jpeg"
    And manifest.BitcoinBlockNumber should be 438712
    And manifest.BitcoinBlockHash should equal "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And manifest.EthereumBlockNumber should equal 2619567
    And manifest.EthereumBlockHash should equal "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And manifest.PreviousIFPSHash should equal "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    And manifest.FileHashes should equal "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And manifest.GUID should equal "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
