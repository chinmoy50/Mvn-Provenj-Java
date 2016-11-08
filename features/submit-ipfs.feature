# features/submit-ipfs.feature
Feature: Submit a payload to IPFS

Scenario: User takes a picture we created manifest need to submit to IPFS
  Given a manifest.json
    and a payload IMG_00001.jpeg
    and an index.html
  When we submit those files to IPFS
  Then they should be stored in IPFS
    and manifest.json should be in the root directory
    and index.html should be in the root directory
    and IMG_00001.jpeg should be in the ./payload directory
    and returns the IPFS hash
