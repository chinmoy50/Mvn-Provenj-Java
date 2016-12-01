package provenj;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import org.json.simple.JSONObject;
import java.io.File;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    // Build manifest
    Manifest manifest = new Manifest();
    File file = null;

    @Given("^a JPEG file named \"([^\"]*)\"$")
    public void a_JPEG_file_named(String filename) throws Throwable {
	manifest.addFile(filename);
    }

    @Given("^the current Bitcoin block number (\\d+)$")
    public void the_current_Bitcoin_block_number(int blockNumber) throws Throwable {
	manifest.setBitcoinBlockNumber(blockNumber);
    }

    @Given("^the current Bitcoin block hash \"([^\"]*)\"$")
    public void the_current_Bitcoin_block_hash(String blockHash) throws Throwable {
	manifest.setBitcoinBlockHash(blockHash);
    }

    @Given("^the current Ethereum block number (\\d+)$")
    public void the_current_Ethereum_block_number(int blockNumber) throws Throwable {
	manifest.setEthereumBlockNumber(blockNumber);
    }

    @Given("^the current Ethereum block hash \"([^\"]*)\"$")
    public void the_current_Ethereum_block_hash(String blockHash) throws Throwable {
	manifest.setEthereumBlockHash(blockHash);
    }

    @Given("^the IPFS hash from the last submitted file \"([^\"]*)\"$")
    public void the_IPFS_hash_from_the_last_submitted_file(String ipfsHash) throws Throwable {
	manifest.setPreviousIPFSHash(ipfsHash);
    }

    @Given("^the other hashes from the last submitted file \"([^\"]*)\"$")
    public void the_other_hashes_from_the_last_submitted_file(String fileHashes) throws Throwable {
	manifest.setPreviousFileHashes(fileHashes);
    }

    @Given("^the GUID for the submission \"([^\"]*)\"$")
    public void the_GUID_for_the_submission(String guid) throws Throwable {
	manifest.setGUID(UUID.fromString(guid));
    }

    @When("^I ask for a manifest file$")
    public void i_ask_for_a_manifest_file() throws Throwable {
        JSONObject json = manifest.get();
    }

    @Then("^manifest\\.FileName should equal \"([^\"]*)\"$")
    public void manifest_FileName_should_equal(String filename) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("FileName"),filename);
    }

    @Then("^manifest\\.BitcoinBlockNumber should be (\\d+)$")
    public void manifest_BitcoinBlockNumber_should_be(int bitcoinBlockNumber) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("BitcoinBlockNumber"),bitcoinBlockNumber);
    }

    @Then("^manifest\\.BitcoinBlockHash should equal \"([^\"]*)\"$")
    public void manifest_BitcoinBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("BitcoinBlockHash"),hash);
    }

    @Then("^manifest\\.EthereumBlockNumber should equal (\\d+)$")
    public void manifest_EthereumBlockNumber_should_equal(int num) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("EthereumBlockNumber"),num);
    }

    @Then("^manifest\\.EthereumBlockHash should equal \"([^\"]*)\"$")
    public void manifest_EthereumBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("EthereumBlockHash"),hash);
    }

    @Then("^manifest\\.PreviousIFPSHash should equal \"([^\"]*)\"$")
    public void manifest_PreviousIFPSHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("PreviousIPFSHash"),hash);
    }

    @Then("^manifest\\.PreviousFileHashes should equal \"([^\"]*)\"$")
    public void manifest_PreviousFileHashes_should_equal(String hashes) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("PreviousFileHashes"),hashes);
    }

    @Then("^manifest\\.GUID should equal \"([^\"]*)\"$")
    public void manifest_GUID_should_equal(String guid) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("GUID"),guid);
    }

    // Apply Exif to JPEG
    @Given("^a JPEG file \"([^\"]*)\"$")
    public void a_JPEG_file(String fileName) throws Throwable {
	file = new File(fileName);
    }

    @Given("^the Bitcoin block number (\\d+)$")
    public void the_Bitcoin_block_number(int blockNumber) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the Bitcoin block hash \"([^\"]*)\"$")
    public void the_Bitcoin_block_hash(String blockHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the Ethereum block number (\\d+)$")
    public void the_Ethereum_block_number(int blockNumber) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the Ethereum block hash \"([^\"]*)\"$")
    public void the_Ethereum_block_hash(String blockHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the IPFS hash from the last file \"([^\"]*)\"$")
    public void the_IPFS_hash_from_the_last_file(String ipfsHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the other hashes from the last file \"([^\"]*)\"$")
    public void the_other_hashes_from_the_last_file(String otherHashes) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the GUID \"([^\"]*)\"$")
    public void the_GUID(String guid) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I load the data from the JPEG file \"([^\"]*)\"$")
    public void i_load_the_data_from_the_JPEG_file(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.BitcoinBlockNumber should match (\\d+)$")
    public void exif_BitcoinBlockNumber_should_match(int blockNumber) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.BitcoinLastBlockHash should equal \"([^\"]*)\"$")
    public void exif_BitcoinLastBlockHash_should_equal(String blockHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.EthereumBlockNumber should equal (\\d+)$")
    public void exif_EthereumBlockNumber_should_equal(int blockNumber) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.EthereumLastBlockHash should equal \"([^\"]*)\"$")
    public void exif_EthereumLastBlockHash_should_equal(String blockHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.ProvenPrevIFPSHandle should equal \"([^\"]*)\"$")
    public void exif_ProvenPrevIFPSHandle_should_equal(String ipfsHash) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.ProvenFileHashes should equal \"([^\"]*)\"$")
    public void exif_ProvenFileHashes_should_equal(String hashes) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Exif\\.ProvenGUID should equal \"([^\"]*)\"$")
    public void exif_ProvenGUID_should_equal(String guid) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
