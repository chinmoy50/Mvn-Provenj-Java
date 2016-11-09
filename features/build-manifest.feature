# features/build-manifest.feature
Feature: Build a manifest of the files supplied to Proven

Scenario: A picture needs to be listed in the manifest.
  Given a JPEG file named IMG_00001.jpeg
  and the current Bitcoin block number btc_block
  and the current Bitcoin block hash btc_hash
  and the current Ethereum block number eth_block
  and the current Ethereum block hash eth_hash
  and the IPFS hash from the last submitted file hash_ipfs
  and the other hashes from the last submitted file file_hashes
  When I ask for a manifest file
  Then manifest.file[0].BitcoinBlockNumber should match btc_block
    and manifest.file[0].BitcoinLastBlockHash should equal btc_hash
    and manifest.file[0].EthereumBlockNumber should equal eth_block
    and manifest.file[0].EthereumLastBlockHash should equal eth_hash
    and manifest.file[0].ProvenPrevIFPSHandle should equal hash_ipfs
    and manifest.file[0].ProvenFileHashes should equal file_hashes

# Problem: "other hash from the last file submitted" doesn't allow for multiple
# files in the same payload
# Possible solution: each file in a manifest contains the file hash of the
# previous file in this manifest (or from any manifest from the same device).
# 2nd possible solution: generate a GUID for each file instead of using a hash
#
Scenario: Multiple pictures need to be added to the manifest in a batch
  Given a JPEG file named IMG_00001.jpeg
  and a JPEG file named IMG_00002.jpeg
  and a JPEG file named IMG_00003.jpeg
  and the current Bitcoin block number btc_block
  and the current Bitcoin block hash btc_hash
  and the current Ethereum block number eth_block
  and the current Ethereum block hash eth_hash
  and the IPFS hash from the last submitted file hash_ipfs
#  and the other hashes from the last submitted file file_hashes
  When I ask for a manifest file
  Then manifest.file[0].BitcoinBlockNumber should match btc_block
    and manifest.file[0].BitcoinLastBlockHash should equal btc_hash
    and manifest.file[0].EthereumBlockNumber should equal eth_block
    and manifest.file[0].EthereumLastBlockHash should equal eth_hash
    and manifest.file[0].ProvenPrevIFPSHandle should equal hash_ipfs
#    and manfiest.file[0].ProvenFileHashes should equal file_hashes
