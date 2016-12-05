package provenj;

import com.google.common.io.ByteStreams;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.UUID;

import java.nio.file.Path;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

public class Stepdefs {

    Metadata metadata = new Metadata();

    // Build manifest
    Manifest manifest = new Manifest();
    String m_fileName;

    @Given("^a JPEG file named \"([^\"]*)\"$")
    public void a_JPEG_file_named(String fileName) throws Throwable {
        manifest.setFileName(fileName);
        m_fileName = fileName;
    }

    @Given("^the hashes for the file \"([^\"]*)\"$")
    public void the_hashes_for_the_file(String fileHashes) throws Throwable {
        manifest.setFileHashes(fileHashes);
    }

    @When("^I ask for a manifest file$")
    public void i_ask_for_a_manifest_file() throws Throwable {
        manifest.copy(metadata);
        JSONObject json = manifest.get();
    }

    @Then("^manifest\\.FileName should equal \"([^\"]*)\"$")
    public void manifest_FileName_should_equal(String filename) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(filename,json.get(ProvenLib.PROVEN_FILE_NAME));
    }

    @Then("^manifest\\.BitcoinBlockNumber should be (\\d+)$")
    public void manifest_BitcoinBlockNumber_should_be(int bitcoinBlockNumber) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(bitcoinBlockNumber,(int)json.get(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER));
    }

    @Then("^manifest\\.BitcoinBlockHash should equal \"([^\"]*)\"$")
    public void manifest_BitcoinBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(hash,json.get(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH));
    }

    @Then("^manifest\\.EthereumBlockNumber should equal (\\d+)$")
    public void manifest_EthereumBlockNumber_should_equal(int num) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(num, (int)json.get(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER));
    }

    @Then("^manifest\\.EthereumBlockHash should equal \"([^\"]*)\"$")
    public void manifest_EthereumBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(hash,json.get(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH));
    }

    @Then("^manifest\\.PreviousIFPSHash should equal \"([^\"]*)\"$")
    public void manifest_PreviousIFPSHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(hash, json.get(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH));
    }

    @Then("^manifest\\.PreviousFileHashes should equal \"([^\"]*)\"$")
    public void manifest_PreviousFileHashes_should_equal(String hashes) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(hashes, json.get(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES));
    }

    @Then("^manifest\\.FileHashes should equal \"([^\"]*)\"$")
    public void manifest_FileHashes_should_equal(String hashes) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(hashes, json.get(ProvenLib.PROVEN_FILE_HASHES));
    }

    @Then("^manifest\\.GUID should equal \"([^\"]*)\"$")
    public void manifest_GUID_should_equal(String guid) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(guid, json.get(ProvenLib.PROVEN_GUID));
    }

    // Apply Exif to JPEG
    ImageTags imageTags = null;
    Path tempOutputFilePath = null;

    @Given("^a JPEG file \"([^\"]*)\"$")
    public void a_JPEG_file(String inputFilePath) throws Throwable {
        File file = new File(inputFilePath);
        manifest.setFileName(file.getName());
        FileInputStream inputFile = new FileInputStream(file);
        File tempOutputFile = File.createTempFile("provenj", ".jpeg");
        tempOutputFile.deleteOnExit();
        tempOutputFilePath = tempOutputFile.toPath();
        FileOutputStream outputFile = new FileOutputStream(tempOutputFile.getCanonicalFile());

        imageTags = new ImageTags(inputFile,outputFile);
    }

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

    private String getTag(String tagName) {
        Runtime rt = Runtime.getRuntime();
        String command = String.format("exiftool -xmp:%1$s -a -b %2$s", tagName, tempOutputFilePath.toString());

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

    @When("^I load the data from the JPEG file returned$")
    public void i_load_the_data_from_the_JPEG_file_returned() throws Throwable {
        imageTags.copy(metadata);
        FileOutputStream outputFile = imageTags.getFile();
        outputFile.close();
    }

    @Then("^Exif\\.BitcoinBlockNumber should match (\\d+)$")
    public void exif_BitcoinBlockNumber_should_match(int blockNumber) throws Throwable {
        assertEquals(Integer.toString(blockNumber), getTag(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER));
    }

    @Then("^Exif\\.BitcoinLastBlockHash should equal \"([^\"]*)\"$")
    public void exif_BitcoinLastBlockHash_should_equal(String blockHash) throws Throwable {
        assertEquals(blockHash, getTag(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH));
    }

    @Then("^Exif\\.EthereumBlockNumber should equal (\\d+)$")
    public void exif_EthereumBlockNumber_should_equal(int blockNumber) throws Throwable {
        assertEquals(Integer.toString(blockNumber), getTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER));
    }

    @Then("^Exif\\.EthereumLastBlockHash should equal \"([^\"]*)\"$")
    public void exif_EthereumLastBlockHash_should_equal(String blockHash) throws Throwable {
        assertEquals(blockHash, getTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH));
    }

    @Then("^Exif\\.ProvenPrevIFPSHandle should equal \"([^\"]*)\"$")
    public void exif_ProvenPrevIFPSHandle_should_equal(String ipfsHash) throws Throwable {
        assertEquals(ipfsHash, getTag(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH));
    }

    @Then("^Exif\\.ProvenFileHashes should equal \"([^\"]*)\"$")
    public void exif_ProvenFileHashes_should_equal(String hashes) throws Throwable {
        assertEquals(hashes, getTag(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES));
    }

    @Then("^Exif\\.ProvenGUID should equal \"([^\"]*)\"$")
    public void exif_ProvenGUID_should_equal(String guid) throws Throwable {
        assertEquals(guid, getTag(ProvenLib.PROVEN_GUID));
    }

    IndexCreator indexCreator;
    String index;


    @When("^I create an index$")
    public void i_create_an_index() throws Throwable {
        manifest.copy(metadata);
        indexCreator = new IndexCreator(manifest);
        index = indexCreator.toString();
        assert(index.matches("^<html>.*</html>$"));
    }

    @Then("^the output file should list the file name$")
    public void the_output_file_should_list_the_file_name() throws Throwable {
        assert(index.matches(String.format("^.*%s.*$", m_fileName)));
    }

    @Then("^the output file should have a static link to the file$")
    public void the_output_file_should_have_a_static_link_to_the_file() throws Throwable {
        assert(index.matches(String.format("^.*a href.*%s.*$",m_fileName)));
    }

    @Then("^the output file should include the last Ethereum hash$")
    public void the_output_file_should_include_the_hash_information_for_the_file() throws Throwable {
        assert(index.matches(String.format("^.*%s.*$",manifest.get().get("EthereumBlockHash"))));
    }

    // Test enclosure creation
    Enclosure enclosure = null;

    @When("^I ask to create an enclosure for an image$")
    public void i_ask_to_create_an_enclosure_for_an_image() throws Throwable {
        // create temporary directory for the enclosure
        enclosure = new Enclosure();

        // apply the metadata to the manifest
        manifest.copy(metadata);

        // apply the metadata to the images
        imageTags.copy(metadata);

        // Grab the image we've tagged
        FileOutputStream outputFile = imageTags.getFile();
        outputFile.close();

        // open the created file to calculate the hash
        FileInputStream finalOutputFile = new FileInputStream(tempOutputFilePath.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32768);
        DigestOutputStream dos = new DigestOutputStream(baos, MessageDigest.getInstance("md5"));
        ByteStreams.copy(finalOutputFile, dos);
        dos.close();

        // put the file in the enclosure
        Path finalOutputFilePath = Paths.get(enclosure.getPath(ProvenLib.PROVEN_PAYLOAD_DIRECTORY).toString(), manifest.getFileName());
        Files.copy(tempOutputFilePath,finalOutputFilePath);

        System.out.println();
        System.out.println();
        System.out.println(finalOutputFilePath);
        System.out.println();
        System.out.println();

        // put the file hash in the manifest
        String hash = DatatypeConverter.printHexBinary(dos.getMessageDigest().digest());
        manifest.setFileHashes(hash);

        // put manifest in the enclosure
        Path manifestFilePath = enclosure.getPath(ProvenLib.PROVEN_MANIFEST);
        Files.write(manifestFilePath, manifest.get().toJSONString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);

        // put index in the enclosure
        Path indexFilePath = enclosure.getPath(ProvenLib.PROVEN_INDEX);
        indexCreator = new IndexCreator(manifest);
        index = indexCreator.toString();
        Files.write(indexFilePath, indexCreator.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }

    @Then("^there should exist a directory$")
    public void there_should_exist_a_directory() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should contain a manifest$")
    public void it_should_contain_a_manifest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should contain an index$")
    public void it_should_contain_an_index() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should contain in the payload directory the file \"([^\"]*)\"$")
    public void it_should_contain_in_the_payload_directory_the_file(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the image should contain the Ethereum block number (\\d+)$")
    public void the_image_should_contain_the_Ethereum_block_number(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the manifest\\.GUID should equal \"([^\"]*)\"$")
    public void the_manifest_GUID_should_equal(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the File Hashes of the image should match the File Hashes in the manifest$")
    public void the_File_Hashes_of_the_image_should_match_the_File_Hashes_in_the_manifest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
