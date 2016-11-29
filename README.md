# provenj

[![Build Status](https://travis-ci.org/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj.svg?branch=master)](https://travis-ci.org/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj)

Java library with API for Proven:
* creating Proven-compliant IPFS payloads
* interacting with Proven smart contracts on Ethereum

## IPFS file layout
Proven stores each submission in a directory in IPFS. This allows us to store
original file names as well as metadata, and allows us to present the file
in a web browser.

`./manifest.json` contains information about the files in the payload and
whatever metadata is associated with each of them, such as file names, types,
digital signatures, hashes, and GUID.

`./index.html` contains a plain HTML list of the files included in the payload. It contains JavaScript to load previews of the file, which runs if JavaScript is available.

`./payload/` contains the actual files being proven.

## Proven smart contract interface
* Submit image for verification
* Check verification by image hash

## Building

Ubuntu prerequisites:
`apt-get install maven`

## Libraries used
https://github.com/ethereum/go-ethereum/wiki/Mobile-Clients:-Libraries-and-Inproc-Ethereum-Nodes
https://bintray.com/karalabe/ethereum/download_file?file_path=geth.aar
