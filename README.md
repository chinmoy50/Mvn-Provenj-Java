# provenj

[![Build Status](https://travis-ci.org/proven-systems/provenj.svg?branch=master)](https://travis-ci.org/proven-systems/provenj)
[![codecov](https://codecov.io/gh/proven-systems/provenj/branch/master/graph/badge.svg)](https://codecov.io/gh/proven-systems/provenj)
[![Release](https://jitpack.io/v/proven-systems/provenj.svg)]
(https://jitpack.io/#proven-systems/provenj)

Java library with APIs for interacting with Proven:
* creating Proven-compliant IPFS payloads and manifests
* submitting those payloads to a local IFPS node (not in-process, external)

This is written in Java 7 with JDK 1.6 and avoids java.nio libraries to provide Android support.

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

An [IPFS.io](https://ipfs.io) daemon is required to be running on the local machine (for testing and for publishing to IPFS).

Ubuntu prerequisites:
`apt-get install maven exiftool curl`

## Usage
You can use this project by building the JAR with `mvn package`, or by using [JitPack](https://jitpack.io/#proven-systems/provenj/) (also supporting Gradle, SBT, etc).

for Maven, you can add the follwing sections to your POM.XML:
```xml
  <repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>com.github.proven-systems</groupId>
      <artifactId>provenj</artifactId>
      <version>v4.1.1</version>
    </dependency>
  </dependencies>
```
In your application:

```java
import provenj.Metadata;
import provenj.Enclosure;
import java.util.UUID;

// Set up the metadata for the picture.
Metadata md = new Metadata();
// You can use Bitcoinj or pull this from a web API
md.setBitcoinBlockNumber(438712);
md.setBitcoinBlockHash("00000000000000000341b8be019c7c1bff721b2d412285ab796ef5ab2ae36213")
// You can use go-ethereum, ethereumj or pull from a web API
md.setEthereumBlockNumber(2619567)
md.setEthereumBlockHash("0xcca5f32676df55171ffabcb32b69eaec0593ce7ae1912228656bd8e213475ecb")
// This is from your previous submission, if any, and if desired
md.setPreviousIPFSHash("Qmb7Uwc39Q7YpPsfkWj54S2rMgdV6D845Sgr75GyxZfV4V")
md.setPreviousFileHashes("84C5B7886D243D0ADBB3C707B629F3BF")
// This is the filename you want published for the attachment
md.setFileName("MyPrettyPicture.jpeg")

// Creates a temporary directory for the resulting picture and
// its metadata
Enclosure enclosure = new Enclosure();

// Applies the tags to a copy of the picture, creates the index and metadata;
// returns any metadata changed or applied, such as file hash & GUID
metadata = enclosure.fill("/path/to/SOURCE_IMG_001.jpeg", metadata);

// Where the image and files where saved
String temporaryFolder = enclosure.getPath();
// Publish the folder to IPFS and return the hash
String ipfsHash = enclosure.publish();
// You might want to save the GUID for tracking status
UUID guid = metadata.getGUID();

// You could save the entire metadata, but you need to keep
// these two pieces of data in order to link any future pictures
// to this one.
String previousFileHashes = metadata.getFileHashes();
String previousIPFShash = ipfsHash;

```
