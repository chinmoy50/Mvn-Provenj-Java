# features/metadata-jpeg.feature
Feature: Apply metadata to JPEG photo

  Scenario: User has taken a picture and needs to add metadata to the JPEG file
    Given a JPEG file "src/test/resources/provenj/2016-12-01-175915.jpg"
    And the Bitcoin block number 438712
    And the Bitcoin block hash "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And the Ethereum block number 2619567
    And the Ethereum block hash "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And the IPFS hash from the last file "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    And the other hashes from the last file "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And the GUID "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
    When I load the data from the JPEG file returned
    Then Exif.BitcoinBlockNumber should match 438712
    And Exif.BitcoinLastBlockHash should equal "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And Exif.EthereumBlockNumber should equal 2619567
    And Exif.EthereumLastBlockHash should equal "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And Exif.ProvenPrevIFPSHandle should equal "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    And Exif.ProvenFileHashes should equal "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And Exif.ProvenGUID should equal "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
