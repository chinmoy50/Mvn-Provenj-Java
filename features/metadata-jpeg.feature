# features/metadata-jpeg.feature
Feature: Apply metadata to JPEG photo

Scenario: User has taken a picture and needs to add metadata to the JPEG file
  Given a JPEG file
  and the current Bitcoin block number btc_block
  and the current Bitcoin block hash btc_hash
  and the current Ethereum block number eth_block
  and the current Ethereum block hash eth_hash
  and the IPFS hash from the last submitted file hash_ipfs
  and the other hashes from the last submitted file file_hashes
  When I apply that data to the JPEG file
  Then Exif.BitcoinBlockNumber should match btc_block
    and Exif.BitcoinLastBlockHash should equal btc_hash
    and Exif.EthereumBlockNumber should equal eth_block
    and Exif.EthereumLastBlockHash should equal eth_hash
    and Exif.ProvenPrevIFPSHandle should equal hash_ipfs
    and Exif.ProvenFileHashes should equal file_hashes
