# features/metadata-jpeg.feature
# Needs Exif library such as ExifTool as used in the Proven POC
Feature: Apply metadata to JPEG photo

Scenario: User has taken a picture and needs to add metadata to the JPEG file
  Given a JPEG file
  And the current Bitcoin block number btc_block
  And the current Bitcoin block hash btc_hash
  And the current Ethereum block number eth_block
  And the current Ethereum block hash eth_hash
  And the IPFS hash from the last submitted file hash_ipfs
  And the other hashes from the last submitted file file_hashes
  And the file GUID
  When I apply that data to the JPEG file
  Then Exif.BitcoinBlockNumber should match btc_block
  And Exif.BitcoinLastBlockHash should equal btc_hash
  And Exif.EthereumBlockNumber should equal eth_block
  And Exif.EthereumLastBlockHash should equal eth_hash
  And Exif.ProvenPrevIFPSHandle should equal hash_ipfs
  And Exif.ProvenFileHashes should equal file_hashes
  And Exif.ProvenGUID should equal GUID
