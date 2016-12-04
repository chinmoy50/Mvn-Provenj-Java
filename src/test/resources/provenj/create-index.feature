# features/create-index.feature
Feature: Create an index.html which lists the files in the payload

  Scenario: Create an index page from a manifest which includes a picture
    Given a manifest.json
    When I create an index
    Then the output file should list the file name
      And the output file should have a static link to the file
      And the output file should include the hash information for the file
# IPNS dependency https://github.com/ipfs/faq/issues/16#issuecomment-232497229
#      And the output file should load a dynamic page if it exists through IPNS 
