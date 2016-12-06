package provenj;

import com.google.common.io.ByteStreams;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import java.nio.file.Path;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

public class Stepdefs {

    Metadata metadata = new Metadata();

    @Given("^the Bitcoin block number (\\d+)$")
    public void the_Bitcoin_block_number(int blockNumber) throws Throwable {
        metadata.setBitcoinBlockNumber(blockNumber);
    }

    @Given("^the Bitcoin block hash \"([^\"]*)\"$")
    public void the_Bitcoin_block_hash(String blockHash) throws Throwable {
        metadata.setBitcoinBlockHash(blockHash);
    }

    @Given("^the Ethereum block number (\\d+)$")
    public void the_Ethereum_block_number(int blockNumber) throws Throwable {
        metadata.setEthereumBlockNumber(blockNumber);
    }

    @Given("^the Ethereum block hash \"([^\"]*)\"$")
    public void the_Ethereum_block_hash(String blockHash) throws Throwable {
        metadata.setEthereumBlockHash(blockHash);
    }

    @Given("^the IPFS hash from the last file \"([^\"]*)\"$")
    public void the_IPFS_hash_from_the_last_file(String ipfsHash) throws Throwable {
        metadata.setPreviousIPFSHash(ipfsHash);
    }

    @Given("^the hashes from the last file \"([^\"]*)\"$")
    public void the_hashes_from_the_last_file(String previousFileHashes) throws Throwable {
        metadata.setPreviousFileHashes(previousFileHashes);
    }

    @Given("^the GUID \"([^\"]*)\"$")
    public void the_GUID(String guid) throws Throwable {
        metadata.setGUID(UUID.fromString(guid));
    }

    // Read XMP tag from JPEG file using exiftool for testing purposes.
    private String getTag(String tagName, Path filePath) {
        Runtime rt = Runtime.getRuntime();
        String command = String.format("exiftool -xmp:%1$s -a -b %2$s", tagName, filePath.toString());

        try {
            Process proc = rt.exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            return stdInput.readLine();
        }
        catch (Exception e) {
            return "";
        }
    }

    protected String calculateFileHash(Path path) throws NoSuchAlgorithmException, IOException {
        FileInputStream stream = new FileInputStream(path.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32768);
        DigestOutputStream dos = new DigestOutputStream(baos, MessageDigest.getInstance("md5"));
        ByteStreams.copy(stream, dos);
        dos.close();
        return DatatypeConverter.printHexBinary(dos.getMessageDigest().digest());
    }

    // Test enclosure creation
    Enclosure enclosure = null;

    @When("^I provide a JPEG file \"([^\"]*)\"$")
    public void i_provide_a_jpeg_file(String inputFilePath) throws Throwable {
        // Get file name
        File inputFile = new File(inputFilePath);
        metadata.setFileName(inputFile.getName());

        // Create temporary output file
        File tempOutputFile = File.createTempFile("provenj", ".jpeg");
        tempOutputFile.deleteOnExit();
        Path tempOutputFilePath = tempOutputFile.toPath();
        FileOutputStream outputFileStream = new FileOutputStream(tempOutputFile.getCanonicalFile());

        // apply the metadata to the images
        ImageTagger imageTagger = new ImageTagger(metadata);
        FileInputStream inputFileStream = new FileInputStream(inputFile);
        imageTagger.tagImage(inputFileStream,outputFileStream);
        inputFileStream.close();
        outputFileStream.close();

        // create temporary directory for the enclosure
        enclosure = new Enclosure();

        // copy the image file into the enclosure content directory
        Path finalOutputFilePath =
            Paths.get(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                      metadata.getFileName());
        Files.copy(tempOutputFilePath,finalOutputFilePath);

        // calculate the image file hash and record it in the metadata
        metadata.setFileHashes(calculateFileHash(tempOutputFilePath));

        // write the manifest to the enclosure
        Manifest manifest = new Manifest(metadata);
        Path manifestFilePath = enclosure.getPath(ProvenLib.PROVEN_MANIFEST);
        Files.write(manifestFilePath,
                    manifest.get().toJSONString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);

        // write the index to the enclosure
        Path indexFilePath = enclosure.getPath(ProvenLib.PROVEN_INDEX);
        IndexCreator indexCreator = new IndexCreator(metadata);
        Files.write(indexFilePath,
                    indexCreator.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
    }

    @Then("^there should exist a directory$")
    public void there_should_exist_a_directory() throws Throwable {
        assert(Files.isDirectory(enclosure.getPath("")));
    }

    @Then("^it should contain a manifest$")
    public void it_should_contain_a_manifest() throws Throwable {
        assert(Files.exists(enclosure.getPath(ProvenLib.PROVEN_MANIFEST)));
    }

    @Then("^it should contain an index$")
    public void it_should_contain_an_index() throws Throwable {
        assert(Files.exists(enclosure.getPath(ProvenLib.PROVEN_INDEX)));
    }

    @Then("^it should contain in the payload directory the file \"([^\"]*)\"$")
    public void it_should_contain_in_the_payload_directory_the_file(String fileName) throws Throwable {
        assert(Files.exists(Paths.get(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                      fileName)));
        assertEquals(fileName,metadata.getFileName());
    }

    @Then("^the image should contain the Ethereum block number (\\d+)$")
    public void the_image_should_contain_the_Ethereum_block_number(int blockNumber) throws Throwable {
        assertEquals(Integer.toString(blockNumber),
                     getTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER,
                            Paths.get(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                                        metadata.getFileName())));
        assertEquals(metadata.getEthereumBlockNumber(),blockNumber);
    }

    JSONObject finalJson = null;

    @Then("^the manifest\\.GUID should equal \"([^\"]*)\"$")
    public void the_manifest_GUID_should_equal(String guid) throws Throwable {
        String json = new String(Files.readAllBytes(enclosure.getPath(ProvenLib.PROVEN_MANIFEST)));
        JSONParser parser = new JSONParser();
        finalJson = (JSONObject) parser.parse(json);
        assertEquals(guid,finalJson.get(ProvenLib.PROVEN_GUID));
        assertEquals(guid,metadata.getGUID().toString());
    }

    @Then("^the File Hashes of the image should match the File Hashes in the manifest$")
    public void the_File_Hashes_of_the_image_should_match_the_File_Hashes_in_the_manifest() throws Throwable {
        assertEquals(finalJson.get(ProvenLib.PROVEN_FILE_HASHES),
                     calculateFileHash(Paths.get(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                                 finalJson.get(ProvenLib.PROVEN_FILE_NAME).toString())));
        assertEquals(finalJson.get(ProvenLib.PROVEN_FILE_HASHES), metadata.getFileHashes());
    }
}
