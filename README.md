# provenj

[![Build Status](https://travis-ci.org/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj.svg?branch=master)](https://travis-ci.org/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj)
[![codecov](https://codecov.io/gh/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj/branch/master/graph/badge.svg)](https://codecov.io/gh/1AmOXsGnfXdbNg3RMTyPCHkn2aT/provenj)

Java library with APIs for interacting with Proven:
* creating Proven-compliant IPFS payloads and manifests

## IPFS file layout
Proven stores each submission in a directory in IPFS. This allows us to store
original file names as well as metadata, and allows us to present the file
in a web browser.

`./manifest.json` contains information about the files in the payload and
whatever metadata is associated with each of them, such as file names, types,
digital signatures, hashes, and GUID.

`./index.html` contains a plain HTML list of the files included in the payload. It contains JavaScript to load previews of the file, which runs if JavaScript is available.

`./payload/` contains the actual files being proven.

## Exif/XMP tagging
This library will tag JPEG images with the Exif/XMP tags standard for Proven verification.  It writes XMP manually to remain pure Java, although [ExifTool](http://www.sno.phy.queensu.ca/~phil/exiftool/) is the clear reference implementation and would have been preferable if it were reliably available for calling on all platforms. Therefore, the tests use Exiftool directly to verify that the tags are written correctly.

## Building

Ubuntu prerequisites:
`apt-get install maven exiftool`

## Command line
The Maven packaging is configured to build a [shaded JAR](https://maven.apache.org/plugins/maven-shade-plugin/usage.html) which contains all of the dependencies, which makes it large.
`mvn package`
`java -cp target/provenj-0.0.1.jar provenj.CmdLine IMG_0001.jpeg -DGUID=7e26a501-30fb-4775-a494-c42691dc21e9 -DBitcoinBlockNumber=10101 -DFileName=MemePic.jpg`
All of the properties in the metadata can optionally be set.  It returns to standard output a path to the temporary directory of the enclosure.
