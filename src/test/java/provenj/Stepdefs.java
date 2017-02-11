package provenj;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class Stepdefs {

    Metadata metadata = new Metadata();
    Enclosure enclosure = null;

    @Given("^the Bitcoin block number (\\d+)$")
    public void the_Bitcoin_block_number(int blockNumber) throws Throwable {
        // testing set-by-string
        metadata.setByTag("BitcoinBlockNumber", "999");
        assertEquals(999, metadata.getBitcoinBlockNumber());
        metadata.setBitcoinBlockNumber(blockNumber);
    }

    @Given("^the Bitcoin block hash \"([^\"]*)\"$")
    public void the_Bitcoin_block_hash(String blockHash) throws Throwable {
        metadata.setByTag("BitcoinBlockHash", "hashtash");
        assertEquals("hashtash", metadata.getBitcoinBlockHash());
        metadata.setBitcoinBlockHash(blockHash);
    }

    @Given("^the Ethereum block number (\\d+)$")
    public void the_Ethereum_block_number(int blockNumber) throws Throwable {
        // testing set-by-string
        metadata.setByTag("EthereumBlockNumber", "8888");
        assertEquals(8888, metadata.getEthereumBlockNumber());
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
        // testing set-by-string
        metadata.setByTag("GUID", "1aa3ded6-7fbc-4cef-8f86-6312ea5aebaa");
        assertEquals(UUID.fromString("1aa3ded6-7fbc-4cef-8f86-6312ea5aebaa"), metadata.getGUID());
        metadata.setGUID(UUID.fromString(guid));
    }

    @Given("^the file name \"([^\"]*)\"$")
    public void the_file_name(String fileName) throws Throwable {
        metadata.setFileName(fileName);
    }

    BitcoinInfo bitcoinInfo = null;

    @Given("^the current Bitcoin block info$")
    public void the_current_Bitcoin_block_info() throws Throwable {
        if (bitcoinInfo == null) bitcoinInfo = new BitcoinInfo();
        metadata = bitcoinInfo.apply(metadata);
    }

    @Then("^the Bitcoin block number everywhere is greater than (\\d+)$")
    public void the_Bitcoin_block_number_everywhere_is_greater_than(int blockNumber) throws Throwable {
        assert(metadata.getBitcoinBlockNumber() > blockNumber);
        assert(finalJson.getInt(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER) > blockNumber);
        assert(Integer.parseInt(getFinalImageTag(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER)) > blockNumber);
    }

    EthereumInfo ethereumInfo = null;

    @Given("^the current Ethereum block info$")
    public void the_current_Ethereum_block_info() throws Throwable {
        if (ethereumInfo == null) ethereumInfo = new EthereumInfo();
        metadata = ethereumInfo.apply(metadata);
    }

    @Then("^the Ethereum block number everywhere is greater than (\\d+)$")
    public void the_Ethereum_block_number_everywhere_is_greater_than(int blockNumber) throws Throwable {
        assert(metadata.getEthereumBlockNumber() > blockNumber);
        assert(finalJson.getInt(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER) > blockNumber);
        assert(Integer.parseInt(getFinalImageTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER)) > blockNumber);
    }

    private String shellCommand(String command){
        Runtime rt = Runtime.getRuntime();

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

    // Read XMP tag from JPEG file using exiftool for testing purposes.
    private String getTag(String tagName, String filePath) {
        return shellCommand(String.format("exiftool -xmp:%1$s -a -b %2$s", tagName, filePath));
    }

    // Verify the MD5 hash independent of the Java implementation
    private String getMD5(String imageFilePath){
        return shellCommand(String.format("md5sum %1$s", imageFilePath));
    }

    // Test enclosure creation
    String ipfsHash = "";

    @When("^I provide a JPEG file \"([^\"]*)\"$")
    public void i_provide_a_jpeg_file(String inputFilePath) throws Throwable {
        enclosure = new Enclosure();
        File file = new File(inputFilePath);
        metadata = enclosure.fill(file, metadata);
        ipfsHash = enclosure.publish();
        System.out.println(ipfsHash);
    }

    @Then("^the IPFS hash returned should be accessible from the IPFS gateway$")
    public void the_IPFS_hash_returned_should_be_accessible_from_the_IPFS_gateway() throws Throwable {
        JSONObject ipfsJson = (JSONObject) new JSONTokener(shellCommand(String.format("curl https://ipfs.io/ipfs/%s/manifest.json",ipfsHash))).nextValue();
        assertEquals(metadata.getFileName(), ipfsJson.getString(ProvenLib.PROVEN_FILE_NAME));
    }

    @Then("^there should exist a directory$")
    public void there_should_exist_a_directory() throws Throwable {
        assert(is_directory(enclosure.getPath().toString()));
    }

    @Then("^it should contain a manifest$")
    public void it_should_contain_a_manifest() throws Throwable {
        assert(file_exists(enclosure.getPath(ProvenLib.PROVEN_MANIFEST).toString()));
    }

    @Then("^it should contain an index$")
    public void it_should_contain_an_index() throws Throwable {
        assert(file_exists(enclosure.getPath(ProvenLib.PROVEN_INDEX).toString()));
    }

    private boolean file_exists(String path){
        File file = new File(path);
        return file.exists();
    }

    private boolean is_directory(String path){
        File file = new File(path);
        return file.isDirectory();
    }

    private String file_path(String path, String fileName){
        return path + System.getProperty("file.separator") + fileName;
    }

    @Then("^it should contain in the payload directory the file \"([^\"]*)\"$")
    public void it_should_contain_in_the_payload_directory_the_file(String fileName) throws Throwable {
        assert(file_exists(file_path(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(), fileName)));
        assertEquals(fileName, metadata.getFileName());
    }

    // For testing, check a tag in the image in the enclosure.
    protected String getFinalImageTag(String tag){
        return getTag(tag, file_path(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                     metadata.getFileName()));
    }

    JSONObject finalJson = null;

    @Then("^the GUID everywhere is \"([^\"]*)\"$")
    public void the_GUID_everywhere_is(String guid) throws Throwable {
        String json = Files.toString(new File(enclosure.getPath(ProvenLib.PROVEN_MANIFEST)), Charsets.UTF_8);
        finalJson = (JSONObject) new JSONTokener(json).nextValue();
        assertEquals(guid, finalJson.getString(ProvenLib.PROVEN_GUID));
        assertEquals(guid, getFinalImageTag(ProvenLib.PROVEN_GUID));
        assertEquals(guid, metadata.getGUID().toString());
    }

    @Then("^the File Hashes are the same everywhere")
    public void the_File_Hashes_are_the_same_everywhere() throws Throwable {
        assertEquals(finalJson.getString(ProvenLib.PROVEN_FILE_HASHES),
                     Enclosure.calculateFileHash(file_path(enclosure.getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                                           finalJson.getString(ProvenLib.PROVEN_FILE_NAME))));
        assertEquals(finalJson.getString(ProvenLib.PROVEN_FILE_HASHES), metadata.getFileHashes());
        assertEquals(metadata.getFileHashes().toUpperCase(),finalJson.getString(ProvenLib.PROVEN_FILE_HASHES).toString().toUpperCase());
        // NOTE: we're not checking tags inside the image because the file hash OF the image can't be IN the image.
    }

    @Then("^the Bitcoin block hash everywhere is \"([^\"]*)\"$")
    public void the_Bitcoin_block_hash_everywhere_is(String blockHash) throws Throwable {
        assertEquals(blockHash, finalJson.getString(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH));
        assertEquals(blockHash, getFinalImageTag(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH));
        assertEquals(blockHash, metadata.getBitcoinBlockHash());
    }

    @Then("^the Bitcoin block number everywhere is (\\d+)$")
    public void the_Bitcoin_block_number_everywhere_is(int blockNumber) throws Throwable {
        assertEquals(blockNumber, finalJson.getInt(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER));
        assertEquals(Integer.toString(blockNumber), getFinalImageTag(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER));
        assertEquals(blockNumber, metadata.getBitcoinBlockNumber());
    }

    @Then("^the Ethereum block hash everywhere is \"([^\"]*)\"$")
    public void the_Ethereum_block_hash_everywhere_is(String blockHash) throws Throwable {
        assertEquals(blockHash, finalJson.getString(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH));
        assertEquals(blockHash, getFinalImageTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH));
        assertEquals(blockHash, metadata.getEthereumBlockHash());
    }

    @Then("^the Ethereum block number everywhere is (\\d+)$")
    public void the_Ethereum_block_number_everywhere_is(int blockNumber) throws Throwable {
        assertEquals(blockNumber, finalJson.getInt(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER));
        assertEquals(Integer.toString(blockNumber), getFinalImageTag(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER));
        assertEquals(blockNumber, metadata.getEthereumBlockNumber());
    }

    @Then("^the last IPFS file hash everywhere is \"([^\"]*)\"$")
    public void the_last_IPFS_file_hash_everywhere_is(String ipfsHash) throws Throwable {
        assertEquals(ipfsHash, finalJson.getString(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH));
        assertEquals(ipfsHash, getFinalImageTag(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH));
        assertEquals(ipfsHash, metadata.getPreviousIPFSHash());
    }

    @Then("^the last file hashes everywhere is \"([^\"]*)\"$")
    public void the_last_file_hashes_everywhere_is(String fileHashes) throws Throwable {
        assertEquals(fileHashes, finalJson.getString(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES));
        assertEquals(fileHashes, getFinalImageTag(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES));
        assertEquals(fileHashes, metadata.getPreviousFileHashes());
    }

    @When("^I ask to directly tag a copy of the JPEG file \"([^\"]*)\"$")
    public void i_ask_to_directly_tag_a_copy_of_the_JPEG_file(String inputFilePath) throws Throwable {
        enclosure = new Enclosure();
        File inputFile = new File(inputFilePath);
        File tempOutputFile = File.createTempFile("stepdefs", ".jpeg");
        Files.copy(inputFile,tempOutputFile);
        assertNotNull(enclosure);
        assertNotNull(metadata);
        assertNotNull(tempOutputFile);
        metadata.setFileName(tempOutputFile.getName());
        metadata = enclosure.fill(tempOutputFile, metadata, true);
        // double check that we didn't mess up the test case and modify the test file
        assertNull(getTag(ProvenLib.PROVEN_FILE_NAME,inputFilePath));
    }

    @When("^I request the most recent Bitcoin block number$")
    public void i_request_the_most_recent_Bitcoin_block_number() throws Throwable {
        if (bitcoinInfo == null) bitcoinInfo = new BitcoinInfo();
        assert (bitcoinInfo.getLastBlockNumber() > 0);
    }

    @Then("^the Bitcoin block number should be greater than (\\d+)$")
    public void the_Bitcoin_block_number_should_be_greater_than(int knownHeight) throws Throwable {
        assert (bitcoinInfo.getLastBlockNumber() > knownHeight);
    }

    @When("^I request the most recent Bitcoin block hash$")
    public void i_request_the_most_recent_Bitcoin_block_hash() throws Throwable {
        if (bitcoinInfo == null) bitcoinInfo = new BitcoinInfo();
        assert (bitcoinInfo.getLastBlockHash().length() > 0);
    }

    @Then("^the Bitcoin block hash should be (\\d+) characters long$")
    public void the_Bitcoin_block_hash_should_be_characters_long(int length) throws Throwable {
        assertEquals(bitcoinInfo.getLastBlockHash().length(), length);
    }

    @When("^I request the most recent Ethereum block number$")
    public void i_request_the_most_recent_Ethereum_block_number() throws Throwable {
        if (ethereumInfo == null) ethereumInfo = new EthereumInfo();
        assert (ethereumInfo.getLastBlockNumber() > 0);
    }

    @Then("^the Ethereum block number should be greater than (\\d+)$")
    public void the_Ethereum_block_number_should_be_greater_than(int blockNumber) throws Throwable {
        assert (ethereumInfo.getLastBlockNumber() > blockNumber);
    }

    @When("^I request the most recent Ethereum block hash$")
    public void i_request_the_most_recent_Ethereum_block_hash() throws Throwable {
        if (ethereumInfo == null) ethereumInfo = new EthereumInfo();
        assert (ethereumInfo.getLastBlockHash().length() > 0);
    }

    @Then("^the Ethereum block hash should be (\\d+) characters long$")
    public void the_Ethereum_block_hash_should_be_characters_long(int length) throws Throwable {
        assertEquals(ethereumInfo.getLastBlockHash().length(), length);
    }

}
