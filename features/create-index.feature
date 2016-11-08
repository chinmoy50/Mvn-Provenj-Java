# features/create-index.feature
Feature: Create an index.html which lists the file

Scenario: User takes a picture we create a manifest need an index page
  Given a manifest.json
  When I create an index
  Then the output should list the file names with static links to the files
    and static links to each file
    and the hash information for each file
