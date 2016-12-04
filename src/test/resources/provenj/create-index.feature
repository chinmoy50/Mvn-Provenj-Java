# features/create-index.feature
Feature: Create an index.html which lists the files in the payload

  Scenario: Create an index page from a manifest which includes a picture
    Given a JPEG file named "IMG_00001.jpeg"
    And the current Bitcoin block number 438712
    And the current Bitcoin block hash "00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213"
    And the current Ethereum block number 2619567
    And the current Ethereum block hash "0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb"
    And the IPFS hash from the last submitted file "QmP1KyrSsD4KGPFRsVxV66cZ95LqhLWGbwCakzRsoKjrTu"
    And the hashes for the last submitted file "dff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And the hashes for the file "eff0c94255cd1f68a824e81005b00f617afecd74c6cccecfbae0d2b7875fabf3"
    And the GUID for the submission "0b89ff5d-c1d8-4dce-949a-a7e29215b09d"
    When I create an index
    Then the output file should list the file name
      And the output file should have a static link to the file
      And the output file should include the last Ethereum hash
# IPNS dependency https://github.com/ipfs/faq/issues/16#issuecomment-232497229
#      And the output file should load a dynamic page if it exists through IPNS 
