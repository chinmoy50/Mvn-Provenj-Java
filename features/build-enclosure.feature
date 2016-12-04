# features/build-enclosure.feature
Feature: Create an enclosure which is a temporary directory that contains all assets to be submitted

  Scenario: Create an enclosure from a picture and metadata
    Given a JPEG file "src/test/resources/provenj/2016-12-01-175915.jpg"
    And the Bitcoin block number 438712
    And the Bitcoin block hash "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And the Ethereum block number 2619567
    And the Ethereum block hash "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And the IPFS hash from the last file "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    And the other hashes from the last file "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And the GUID "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
    When I ask to create an enclosure for an image
    Then there should exist a directory
    And it should contain a manifest
    And it should contain an index
    And it should contain in the payload directory the file "2016-12-01-175915.jpg"
    And the image should contain the Ethereum block number 2619567
    And the manifest.GUID should equal "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
    And the File Hashes of the image should match the File Hashes in the manifest
