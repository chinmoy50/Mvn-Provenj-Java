# features/create-index.feature
Feature: Create an index.html which lists the files in the payload

Scenario: User takes a picture we create a manifest need an index page
  Given a manifest.json
  When I create an index
  Then the output file should list the file names with static links to the files
    and the output file should list static links to each file
    and the output file should list the hash information for each file
# IPNS dependency https://github.com/ipfs/faq/issues/16#issuecomment-232497229
#    and the output file should load a dynamic page if it exists through IPNS 
